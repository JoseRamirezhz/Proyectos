package sv.edu.utec.appsupermercadosprecios;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Recuperar_password extends AppCompatActivity {

    Button recuperarButton;
    EditText txtemail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar_password);

        recuperarButton = findViewById(R.id.btbRecuperar);
        txtemail = findViewById(R.id.txtUsuario);

        recuperarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate();
            }
        });
    }

    public  void  validate(){
        String v_email = txtemail.getText().toString().trim();

        if(v_email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(v_email).matches()){
            txtemail.setError("Correo invalido");
            return;
        }

        sendEmail(v_email);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent inten = new Intent(Recuperar_password.this, MainActivity.class);
        startActivity(inten);
        finish();
    }

    public void sendEmail(String email){
        FirebaseAuth auth = FirebaseAuth.getInstance();
        String emailAddress = email;

        auth.sendPasswordResetEmail(emailAddress)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(Recuperar_password.this,"¡Correo Enviado!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Recuperar_password.this, MainActivity.class);
                            startActivity(intent);
                        }else {
                            Toast.makeText(Recuperar_password.this,"¡Correo invalido!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
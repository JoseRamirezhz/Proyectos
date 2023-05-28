package sv.edu.utec.appsupermercadosprecios;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.ktx.Firebase;

public class MainActivity extends AppCompatActivity {

    Button btnIniciar;
    TextView edtUsuario, edtPassword, passOlvida;

    DataBaseHelper dataBaseHelper;
    AwesomeValidation awesomeValidation;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnIniciar = findViewById(R.id.btnIngresar);
        passOlvida = findViewById(R.id.tvPassOlvidada);
        edtUsuario = findViewById(R.id.txtUsuario);
        edtPassword = findViewById(R.id.txtPassword);

        firebaseAuth = FirebaseAuth.getInstance();

        //condicional si ya existe una session abierta
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        if(user != null){
            irAMenu();
        }

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        awesomeValidation.addValidation(this,R.id.txtUsuario, Patterns.EMAIL_ADDRESS, R.string.mail_validacion);
        awesomeValidation.addValidation(this,R.id.txtPassword, ".{6,}",R.string.password_validacion);



        dataBaseHelper = new DataBaseHelper(this);

        btnIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(awesomeValidation.validate()){
                    String usuario = edtUsuario.getText().toString();
                    String password = edtPassword.getText().toString();

                    firebaseAuth.signInWithEmailAndPassword(usuario,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            //
                            if (task.isSuccessful()){
                                irAMenu();
                                Toast.makeText(MainActivity.this,"¡Bienvenido!", Toast.LENGTH_SHORT).show();
                                finish();
                            }else{
                                String errorCodigo = ((FirebaseAuthException) task.getException()).getErrorCode();
                                dameToastdeerror(errorCodigo);
                            }
                        }
                    });
                }else{

                }
            }
        });

        /*
        btnIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usuario = edtUsuario.getText().toString();
                String password = edtPassword.getText().toString();

                if(usuario.equals("") || password.equals("")){
                    Toast.makeText(MainActivity.this,"Todo los campos son obligatorios", Toast.LENGTH_SHORT).show();
                }else {
                    Boolean checkCedenciales = dataBaseHelper.checkUsuarioPassword(usuario,password);

                    if (checkCedenciales == true){
                        Toast.makeText(MainActivity.this,"Inicio de Session Con Existo", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), menuPrincipal.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(MainActivity.this,"Credenciales invalidas", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
*/
        passOlvida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Recuperar_password.class);
                startActivity(intent);
                finish();
            }
        });



    }

        public void irAMenu() {
            Intent i = new Intent(getApplicationContext(),menuPrincipal.class);
            i.putExtra("usuario", edtUsuario.getText().toString());
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);//Evita que se creen nuevas actividades
            startActivity(i);

        }

    public void irARegistro(View v) {
        //Intent i = new Intent(getApplicationContext(),_6_crearUsuario.class);
        Intent i = new Intent(getApplicationContext(),Nueva_Cuenta.class);
        startActivity(i);

    }

    private void dameToastdeerror(String error) {

        switch (error) {

            case "ERROR_INVALID_CUSTOM_TOKEN":
                Toast.makeText(MainActivity.this, "El formato del token personalizado es incorrecto. Por favor revise la documentación", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_CUSTOM_TOKEN_MISMATCH":
                Toast.makeText(MainActivity.this, "El token personalizado corresponde a una audiencia diferente.", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_INVALID_CREDENTIAL":
                Toast.makeText(MainActivity.this, "La credencial de autenticación proporcionada tiene un formato incorrecto o ha caducado.", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_INVALID_EMAIL":
                Toast.makeText(MainActivity.this, "La dirección de correo electrónico está mal formateada.", Toast.LENGTH_LONG).show();
                //edtUsuario.setError("La dirección de correo electrónico está mal formateada.");
                edtUsuario.requestFocus();
                break;

            case "ERROR_WRONG_PASSWORD":
                Toast.makeText(MainActivity.this, "La contraseña no es válida o el usuario no tiene contraseña.", Toast.LENGTH_LONG).show();
                //edtPassword.setError("la contraseña es incorrecta ");
                edtPassword.requestFocus();
                edtPassword.setText("");
                break;

            case "ERROR_USER_MISMATCH":
                Toast.makeText(MainActivity.this, "Las credenciales proporcionadas no corresponden al usuario que inició sesión anteriormente..", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_REQUIRES_RECENT_LOGIN":
                Toast.makeText(MainActivity.this,"Esta operación es sensible y requiere autenticación reciente. Inicie sesión nuevamente antes de volver a intentar esta solicitud.", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_ACCOUNT_EXISTS_WITH_DIFFERENT_CREDENTIAL":
                Toast.makeText(MainActivity.this, "Ya existe una cuenta con la misma dirección de correo electrónico pero diferentes credenciales de inicio de sesión. Inicie sesión con un proveedor asociado a esta dirección de correo electrónico.", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_EMAIL_ALREADY_IN_USE":
                Toast.makeText(MainActivity.this, "La dirección de correo electrónico ya está siendo utilizada por otra cuenta..   ", Toast.LENGTH_LONG).show();
                //edtUsuario.setError("La dirección de correo electrónico ya está siendo utilizada por otra cuenta.");
                edtUsuario.requestFocus();
                break;

            case "ERROR_CREDENTIAL_ALREADY_IN_USE":
                Toast.makeText(MainActivity.this, "Esta credencial ya está asociada con una cuenta de usuario diferente.", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_USER_DISABLED":
                Toast.makeText(MainActivity.this, "La cuenta de usuario ha sido inhabilitada por un administrador..", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_USER_TOKEN_EXPIRED":
                Toast.makeText(MainActivity.this, "La credencial del usuario ya no es válida. El usuario debe iniciar sesión nuevamente.", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_USER_NOT_FOUND":
                Toast.makeText(MainActivity.this, "No hay ningún registro de usuario que corresponda a este identificador. Es posible que se haya eliminado al usuario.", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_INVALID_USER_TOKEN":
                Toast.makeText(MainActivity.this, "La credencial del usuario ya no es válida. El usuario debe iniciar sesión nuevamente.", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_OPERATION_NOT_ALLOWED":
                Toast.makeText(MainActivity.this, "Esta operación no está permitida. Debes habilitar este servicio en la consola.", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_WEAK_PASSWORD":
                Toast.makeText(MainActivity.this, "La contraseña proporcionada no es válida..", Toast.LENGTH_LONG).show();
                //edtPassword.setError("La contraseña no es válida, debe tener al menos 6 caracteres");
                edtPassword.requestFocus();
                break;
        }

    }

}
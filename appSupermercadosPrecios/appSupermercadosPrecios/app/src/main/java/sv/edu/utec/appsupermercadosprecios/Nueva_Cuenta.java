package sv.edu.utec.appsupermercadosprecios;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.auth.FirebaseAuthCredentialsProvider;
import com.google.firebase.ktx.Firebase;

import java.util.regex.Pattern;

public class Nueva_Cuenta extends AppCompatActivity {
    Button btnCrearCuenta;
    EditText txtUser,txtpass1, txtpass2;
    TextView tvinicioSesion;

    Context contexto;

    FirebaseAuth firebaseAuth;
    AwesomeValidation awesomeValidation;

    //DataBaseHelper dataBaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_cuenta);

        firebaseAuth = FirebaseAuth.getInstance();
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        awesomeValidation.addValidation(this,R.id.txtUsuario, Patterns.EMAIL_ADDRESS, R.string.mail_validacion);
        awesomeValidation.addValidation(this,R.id.txtPassword, ".{6,}",R.string.password_validacion);



        btnCrearCuenta = findViewById(R.id.btnCrearCuenta);
        tvinicioSesion = findViewById(R.id.tvInicioSesion);
        txtUser = findViewById(R.id.txtUsuario);
        txtpass1 = findViewById(R.id.txtPassword);
        //txtpass2 = findViewById(R.id.txtPasswordValidar);

        btnCrearCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usuario = txtUser.getText().toString();
                String password = txtpass1.getText().toString();

                if(awesomeValidation.validate()){
                    firebaseAuth.createUserWithEmailAndPassword(usuario,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(Nueva_Cuenta.this,"Usuario creado con exito", Toast.LENGTH_SHORT).show();
                                finish();
                            }else {
                                String errorCodigo = ((FirebaseAuthException) task.getException()).getErrorCode();
                                dameToastdeerror(errorCodigo);
                            }
                        }
                    });
                }else{
                    Toast.makeText(Nueva_Cuenta.this,"Completar todo los campos", Toast.LENGTH_SHORT).show();
                }
            }
        });


/*
        btnCrearCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Boolean codSAve = dataBaseHelper.insertData(txtUser.getText().toString(),txtpass1.getText().toString());


                String usuario = txtUser.getText().toString();
                String password = txtpass1.getText().toString();
                String passwordConfirmacion = txtpass2.getText().toString();

                if (usuario.equals("") || password.equals("") || passwordConfirmacion.equals("")){
                    Toast.makeText(Nueva_Cuenta.this,"Todo los campos son obligatorios", Toast.LENGTH_SHORT).show();
                }else{
                    if (usuario.equals(passwordConfirmacion)){
                        Boolean checkUsuario = dataBaseHelper.chexhUsuario(usuario);

                        if(checkUsuario == false){
                            Boolean insert = dataBaseHelper.insertData(usuario,password);

                            if (insert == true){
                                Toast.makeText(Nueva_Cuenta.this,"Insertado Con Exito", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(Nueva_Cuenta.this,"Error al insertar", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(Nueva_Cuenta.this,"Usuario ya existe, Por favor ingrese usuario", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(Nueva_Cuenta.this,"Contraseña incorrecta", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });*/

        tvinicioSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        });

    }

    public void irALogin(View v) {
        //Intent i = new Intent(getApplicationContext(),_6_crearUsuario.class);
        Intent i = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(i);

    }

    private void dameToastdeerror(String error) {

        switch (error) {

            case "ERROR_INVALID_CUSTOM_TOKEN":
                Toast.makeText(Nueva_Cuenta.this, "El formato del token personalizado es incorrecto. Por favor revise la documentación", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_CUSTOM_TOKEN_MISMATCH":
                Toast.makeText(Nueva_Cuenta.this, "El token personalizado corresponde a una audiencia diferente.", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_INVALID_CREDENTIAL":
                Toast.makeText(Nueva_Cuenta.this, "La credencial de autenticación proporcionada tiene un formato incorrecto o ha caducado.", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_INVALID_EMAIL":
                Toast.makeText(Nueva_Cuenta.this, "La dirección de correo electrónico está mal formateada.", Toast.LENGTH_LONG).show();
                //txtUser.setError("La dirección de correo electrónico está mal formateada.");
                txtUser.requestFocus();
                break;

            case "ERROR_WRONG_PASSWORD":
                Toast.makeText(Nueva_Cuenta.this, "La contraseña no es válida o el usuario no tiene contraseña.", Toast.LENGTH_LONG).show();
                //txtpass1.setError("la contraseña es incorrecta ");
                txtpass1.requestFocus();
                txtpass1.setText("");
                break;

            case "ERROR_USER_MISMATCH":
                Toast.makeText(Nueva_Cuenta.this, "Las credenciales proporcionadas no corresponden al usuario que inició sesión anteriormente..", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_REQUIRES_RECENT_LOGIN":
                Toast.makeText(Nueva_Cuenta.this,"Esta operación es sensible y requiere autenticación reciente. Inicie sesión nuevamente antes de volver a intentar esta solicitud.", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_ACCOUNT_EXISTS_WITH_DIFFERENT_CREDENTIAL":
                Toast.makeText(Nueva_Cuenta.this, "Ya existe una cuenta con la misma dirección de correo electrónico pero diferentes credenciales de inicio de sesión. Inicie sesión con un proveedor asociado a esta dirección de correo electrónico.", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_EMAIL_ALREADY_IN_USE":
                Toast.makeText(Nueva_Cuenta.this, "La dirección de correo electrónico ya está siendo utilizada por otra cuenta..   ", Toast.LENGTH_LONG).show();
                //txtUser.setError("La dirección de correo electrónico ya está siendo utilizada por otra cuenta.");
                //txtUser.setTextColor(Color.rgb(0,0,200));
                txtUser.requestFocus();
                break;

            case "ERROR_CREDENTIAL_ALREADY_IN_USE":
                Toast.makeText(Nueva_Cuenta.this, "Esta credencial ya está asociada con una cuenta de usuario diferente.", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_USER_DISABLED":
                Toast.makeText(Nueva_Cuenta.this, "La cuenta de usuario ha sido inhabilitada por un administrador..", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_USER_TOKEN_EXPIRED":
                Toast.makeText(Nueva_Cuenta.this, "La credencial del usuario ya no es válida. El usuario debe iniciar sesión nuevamente.", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_USER_NOT_FOUND":
                Toast.makeText(Nueva_Cuenta.this, "No hay ningún registro de usuario que corresponda a este identificador. Es posible que se haya eliminado al usuario.", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_INVALID_USER_TOKEN":
                Toast.makeText(Nueva_Cuenta.this, "La credencial del usuario ya no es válida. El usuario debe iniciar sesión nuevamente.", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_OPERATION_NOT_ALLOWED":
                Toast.makeText(Nueva_Cuenta.this, "Esta operación no está permitida. Debes habilitar este servicio en la consola.", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_WEAK_PASSWORD":
                Toast.makeText(Nueva_Cuenta.this, "La contraseña proporcionada no es válida..", Toast.LENGTH_LONG).show();
                //txtpass1.setError("La contraseña no es válida, debe tener al menos 6 caracteres");
                txtpass1.requestFocus();
                break;
        }

    }
}
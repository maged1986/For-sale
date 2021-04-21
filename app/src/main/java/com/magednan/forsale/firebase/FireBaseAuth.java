package com.magednan.forsale.firebase;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.magednan.forsale.models.User;
import com.magednan.forsale.util.SessionManager;

import javax.inject.Inject;

public class FireBaseAuth extends AppCompatActivity {

    private static final String TAG = "FireBaseManager";
    private static final int RC_SIGN_IN = 9001;
    Boolean authentication = false;
    Boolean operation;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private User mUser;


    @Inject
    public FireBaseAuth() {
    }


    public Boolean createNewUser(String name, String country, String state_province, String city,
                                 String email, String password, Context context
    ) {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //send email verificaiton
                            sendVerificationEmail(context);
                            updateUserData(name, country, state_province, city, email);
                            //add user details to firebase database
                                SessionManager manager = new SessionManager(context);
                                manager.setIsLoggedIn(true);
                            Toast.makeText(context, "Successful ",
                                    Toast.LENGTH_SHORT).show();
                            authentication = true;

                        }
                        if (!task.isSuccessful()) {
                            Toast.makeText(context, "Someone with that email ",
                                    Toast.LENGTH_SHORT).show();
                            authentication = false;
                        }
                    }
                });
        return authentication;
    }

    public void updateUserData(String name, String country, String state_province, String city, String email) {
        //add data to the "users" node
        String userid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        mUser = new User(userid, name, country, state_province, city, email);
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        //insert into users node
        reference.child("users").push()
                .setValue(mUser);
    }

    public void sendVerificationEmail(Context mContext) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            user.sendEmailVerification()
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {

                            } else {
                                Toast.makeText(mContext, "couldn't send a verification email", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }

    }

    public Boolean logIn(String email, String password, Context context) {
        FirebaseAuth mAuth;
        mAuth = FirebaseAuth.getInstance();
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                             SessionManager manager = new SessionManager(context);
                              manager.setIsLoggedIn(true);
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            authentication = true;
                            Toast.makeText(context, "Authentication successe.",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(context, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            authentication = false;
                        }
                    }
                });
        return authentication;
    }

    public void signOut(Context context) {
        FirebaseAuth mAuth;
        mAuth = FirebaseAuth.getInstance();
        mAuth.signOut();
        SessionManager manager = new SessionManager(context);
        manager.setIsLoggedIn(false);
    }
}

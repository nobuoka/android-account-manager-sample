package client.auth.sample.app.android.vividcode.info.myauthclientapplication;

import android.Manifest;
import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        p1();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        p1();
    }

    private void p1() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.GET_ACCOUNTS) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{ Manifest.permission.GET_ACCOUNTS }, 111);
            }
        }

        final AccountManager accountManager = AccountManager.get(this);
        {
            Account[] accounts = accountManager.getAccounts();
            for (Account account : accounts) {
                Log.d("vvv", "アカウント情報 : " + account);
            }
        }
        Account[] accounts = accountManager.getAccountsByType("com.example.test");
        for (Account account : accounts) {
            Log.d("vvv", "アカウント情報 : " + account);
        }
        if (accounts.length == 0) {
            Log.d("vvv", "アカウントが 1 個もないので落ちますね");
            return;
        }

        final Account account = new Account("test", "com.example.test");//accounts[0];
        new Thread() {
            @Override
            public void run() {
                try {
                    String dd = accountManager.blockingGetAuthToken(account, "ababa", false);
                    Log.d("vvv", "とったどー:" + dd);
                } catch (OperationCanceledException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (AuthenticatorException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

}

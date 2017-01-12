package server.auth.sample.app.android.vividcode.info.myauthenticatorapplication;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*
        setContentView(R.layout.activity_login);

        final EditText nameEdit = (EditText) findViewById(R.id.name);
        final EditText passwordEdit = (EditText) findViewById(R.id.password);
        Button button = (Button) findViewById(R.id.login);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEdit.getText().toString();
                String password = passwordEdit.getText().toString();
                login(name, password);
            }
        });
        */
    }

    @Override
    protected void onResume() {
        super.onResume();

        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        loginSuccess("test", "password");
                    }
                });
            }
        }.start();
    }

    // ログイン処理
    public void login(final String name, final String password) {
        // TODO:このメソッドは非同期の通信処理でログインを試みます。
        // ログインに成功した場合、loginSuccess()を呼び出します。
        loginSuccess(name, password);
    }

    // ログイン処理のコールバック
    public void loginSuccess(final String name, final String password) {
        Account account = new Account(name, getString(R.string.account_authenticator_account_type));
        AccountManager am = AccountManager.get(this);
        // アカウント情報を保存
        // TODO:本来はパスワードを暗号化する必要があります
        am.addAccountExplicitly(account, password, null);

        // 認証画面終了
        setResult(RESULT_OK);
        finish();
    }
}

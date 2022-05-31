package org.javacream.android.resources;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec .SecretKeySpec;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class EncryptActivity extends Activity {

	private Button showEncryptedButton;
	private Button showDecryptedButton;
	private byte[] encrypted;
	private String decrypted;
	private Cipher c;
	private SecretKeySpec ks;

	@Override
	protected void onStart() {
		super.onStart();
		String mySecretMessage = "Hello World!";
		ks = new SecretKeySpec(getKey(), "AES");
		try {
			c = Cipher.getInstance("AES");
		} catch (Exception e) {
			Log.e("CRYPT", "ERROR", e);
		}
		encrypted = encrypt(mySecretMessage);
		decrypted = decrypt(encrypted);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.encrypted);
		showEncryptedButton = (Button) findViewById(R.id.showEncryptedPreferenceButton);
		showDecryptedButton = (Button) findViewById(R.id.showDecryptedPreferenceButton);

		showEncryptedButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Toast.makeText(EncryptActivity.this,
						"ENCRYPTED PREFERENCE is " + new String(encrypted),
						Toast.LENGTH_LONG).show();

			}
		});
		showDecryptedButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Toast.makeText(EncryptActivity.this,
						"ENCRYPTED PREFERENCE is " + decrypted,
						Toast.LENGTH_LONG).show();
			}
		});

	}

	private byte[] getKey() {
		byte[] seed = "here_is_your_aes_key".getBytes();
		KeyGenerator kg;
		try {
			kg = KeyGenerator.getInstance("AES");
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
		SecureRandom sr;
		try {
			sr = SecureRandom.getInstance("SHA1PRNG");
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
		sr.setSeed(seed);
		kg.init(128, sr);
		SecretKey sk = kg.generateKey();
		byte[] key = sk.getEncoded();
		return key;
	}

	private byte[] encrypt(String clearText) {
		byte[] encryptedText = null;
		try {
			c.init(Cipher.ENCRYPT_MODE, ks);
			encryptedText = c.doFinal(clearText.getBytes("UTF-8"));
			return encryptedText;
		} catch (Exception e) {
			return null;
		}

	}

	private String decrypt(byte[] encryptedText) {

		byte[] clearText = null;
		try {
			c.init(Cipher.DECRYPT_MODE, ks);
			clearText = c.doFinal(encryptedText);
			return new String(clearText, "UTF-8");
		} catch (Exception e) {
			Log.e("EncryptedPreferences", "EXCEPTION IN DECRYPT", e);
			return null;
		}
	}
}
package com.thomasrangelbugs.cadastroconteudos.utils;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class PermissaoHelper {

    public static void solicitarPermissaoNotificacaoSeNecessario(AppCompatActivity activity) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {
            return;
        }

        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.POST_NOTIFICATIONS)
                == PackageManager.PERMISSION_GRANTED) {
            return;
        }

        ActivityResultLauncher<String> launcher = activity.registerForActivityResult(
                new ActivityResultContracts.RequestPermission(),
                isGranted -> {
                    // Nenhuma ação adicional é necessária para este projeto.
                }
        );
        launcher.launch(Manifest.permission.POST_NOTIFICATIONS);
    }
}

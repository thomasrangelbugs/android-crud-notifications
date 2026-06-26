package com.thomasrangelbugs.cadastroconteudos.utils;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;

import com.thomasrangelbugs.cadastroconteudos.R;
import com.thomasrangelbugs.cadastroconteudos.ui.MainActivity;

public class NotificacaoHelper {

    private static final String CHANNEL_ID = "canal_cadastros";
    private static final String CHANNEL_NAME = "Avisos de cadastros";

    private NotificacaoHelper() {
    }

    public static void mostrarNotificacaoMultiploDeDez(Context context, int totalConteudos) {
        criarCanalSeNecessario(context);

        Intent intent = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(
                context,
                0,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE
        );

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle(context.getString(R.string.notificacao_titulo))
                .setContentText(context.getString(R.string.notificacao_texto, totalConteudos))
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText(context.getString(R.string.notificacao_texto_expandido, totalConteudos)))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU
                && ContextCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS)
                != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        NotificationManagerCompat.from(context).notify(totalConteudos, builder.build());
    }

    private static void criarCanalSeNecessario(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID,
                    CHANNEL_NAME,
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            channel.setDescription("Canal usado para avisar quando o total de itens chega a múltiplos de 10.");

            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(channel);
            }
        }
    }
}

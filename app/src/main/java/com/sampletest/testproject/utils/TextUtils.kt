package com.sampletest.testproject.utils

import android.net.Uri
import android.os.Build
import android.text.Html
import android.text.Spanned


class TextUtils {
    fun parseHtml(text: String): Spanned {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(text, Html.FROM_HTML_MODE_LEGACY);
        } else {
            Html.fromHtml(text);
        }
    }

    fun getUrl(link: String): String {
        // No Time to build URI, sorry
        return "https://cabaca.id:8443/api/v2/files/$link&api_key=32ded42cfffb77dee86a29f43d36a3641849d4b5904aade9a79e9aa6cd5b5948"
    }
}
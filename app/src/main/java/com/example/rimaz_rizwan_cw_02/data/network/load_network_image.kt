import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.BufferedInputStream
import java.net.HttpURLConnection
import java.net.URL

suspend fun getBitmapPicture(pictureUrl: String): ImageBitmap {
    return withContext(Dispatchers.IO) {
        val url = URL(pictureUrl)
        val connection = url.openConnection() as HttpURLConnection
        val bufferedInputStream = BufferedInputStream(connection.inputStream)
        val bitmap: Bitmap = BitmapFactory.decodeStream(bufferedInputStream)
        connection.disconnect()
        bitmap.asImageBitmap()
    }
}

@Composable
fun loadNetworkImage(url: String, modifier: Modifier = Modifier): ImageBitmap? {
    var image by remember { mutableStateOf<ImageBitmap?>(null) }
    var loading by remember { mutableStateOf(true) }

    LaunchedEffect(url) {
        image = getBitmapPicture(url)
        loading = false
    }

    return image
}

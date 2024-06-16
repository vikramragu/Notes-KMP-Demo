import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.app.academy.App

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "KMPNotes",
    ) {
        App()
    }
}

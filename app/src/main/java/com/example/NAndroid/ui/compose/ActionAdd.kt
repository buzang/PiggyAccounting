import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

@Composable
fun ActionAdd(selectedOption: Int, onOptionSelected: (Int) -> Unit) {
    val options = listOf("收入", "支出")

    Row {
        options.forEachIndexed { index, option ->
            Button(
                onClick = { onOptionSelected(index) },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = if (index == selectedOption) Color(
                        155,
                        89,
                        182
                    ) else Color.White,
                    contentColor = Color.Black
                ),
                shape = when (index) {
                    0 -> RoundedCornerShape(topStart = 5.dp, bottomStart = 5.dp) // 左边按钮
                    else -> RoundedCornerShape(topEnd = 5.dp, bottomEnd = 5.dp) // 右边按钮
                },
                border = BorderStroke(0.5.dp, Color.Black),
                modifier = Modifier.width(112.dp)
            ) {
                Text(
                    option,
                    style = TextStyle(color = if (index == selectedOption) Color.White else Color.Black)
                )
            }

        }
    }
}

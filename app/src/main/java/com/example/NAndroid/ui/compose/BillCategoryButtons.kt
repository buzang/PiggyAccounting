import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import com.example.NAndroid.data.entities.Bill
import com.example.NAndroid.data.entities.BillCategory
import com.example.NAndroid.utlis.DBHelper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun BillTypeSelectionButtons(
    optionsList: List<BillCategory>,
    selectedOption: Int,
    callback: (Int) -> Unit
) {
    LazyRow(
        modifier = Modifier.padding(top = 10.dp)
    ) {
        items(optionsList.size) { index ->
            val option = optionsList[index]
            Button(
                onClick = { callback(option.uid) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (option.uid == selectedOption) Color(
                        155,
                        89,
                        182
                    ) else Color.White,
                    contentColor = Color.Black
                ),
                modifier = Modifier
                    .padding(5.dp)
                    .shadow(4.dp)
                    .size(width = 85.dp, height = 35.dp),
                shape = when (index) {
                    0 -> RoundedCornerShape(topStart = 0.dp, bottomStart = 0.dp)
                    else -> RoundedCornerShape(topEnd = 0.dp, bottomEnd = 0.dp)
                },
            ) {
                Text(
                    option.category,
                    style = TextStyle(
                        color = if (option.uid == selectedOption) Color.White else Color.Black,
                        fontSize = 12.sp
                    )
                )
            }
        }
    }
}


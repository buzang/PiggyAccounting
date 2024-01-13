package com.example.NAndroid.ui.compose

import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyDateTimePicker(
    openDialog: MutableState<Boolean>,
    datePickerState: DatePickerState,
    success: () -> Unit
) {
    val snackScope = rememberCoroutineScope()

    val snackState = remember { SnackbarHostState() }

    val confirmEnabled = remember {
        derivedStateOf { datePickerState.selectedDateMillis != null }
    }

    SnackbarHost(hostState = snackState, Modifier)

    DatePickerDialog(
        modifier = Modifier.shadow(5.dp),
        onDismissRequest = {
            openDialog.value = false
        },
        confirmButton = {
            TextButton(
                onClick = {
                    success()
                },
                enabled = confirmEnabled.value
            ) {
                Text("OK")
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    openDialog.value = false
                }
            ) {
                Text("Cancel")
            }
        }
    ) {
        DatePicker(state = datePickerState)
    }
}
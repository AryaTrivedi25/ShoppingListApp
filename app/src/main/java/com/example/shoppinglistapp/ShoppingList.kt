package com.example.shoppinglistapp

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

data class ShoppingItem(val id : Int,
                        var name: String,
                        var quantity : Int,
                        var isEditing : Boolean = false
)

@Composable
fun ShoppingListApp(){
    var sIteams by remember{ mutableStateOf(listOf<ShoppingItem>()) }
    var ShowDialog by remember {mutableStateOf(false)}
    var IteamName by remember { mutableStateOf("")}
    var IteamQuantity by remember { mutableStateOf("")}

    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ){
        Button(
            onClick = { ShowDialog = true },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            val doubleNumber : (Int) -> Int = { 2*it }

            Text(text = doubleNumber(5).toString())
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            items(sIteams) {
                ShoppingListItem(it, {}, {})
            }

        }

    }
    if(ShowDialog){
        AlertDialog(onDismissRequest = { ShowDialog = false },
            confirmButton = {
                            Row (
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ){
                                Button(onClick = {
                                    if(IteamName.isNotBlank()){
                                        val newIteam = ShoppingItem(
                                            id = sIteams.size+1,
                                            name = IteamName,
                                            quantity = IteamQuantity.toInt()
                                        )
                                        sIteams = sIteams + newIteam
                                        ShowDialog = false
                                        IteamName = ""
                                    }

                                }) {
                                    Text("Add")
                                }
                                Button(onClick = {ShowDialog = false}) {
                                    Text("Cancel")
                                }
                            }
            },
            title = {Text("Add Shopping Item.")},
            text = {
                Column {
                    OutlinedTextField(value = IteamName,
                        onValueChange = {IteamName = it},
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                        )
                    OutlinedTextField(value = IteamQuantity,
                        onValueChange = {IteamQuantity = it},
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    )
                }
            }
            )
    }
}

@Composable
fun ShoppingListItem(
    item : ShoppingItem,
    onClick: () -> Unit,
    onDeleteClick: () -> Unit,
){

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .border(
                border = BorderStroke(2.dp, Color(0XFF018786)),
                shape = RoundedCornerShape(20)
            )
    ){

        Text(text = item.name, modifier = Modifier.padding(8.dp))


    }
}
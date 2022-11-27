package com.anurag.notekeeperdatabasedemo.ui.delete

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.lifecycle.ViewModelProvider
import com.anurag.notekeeperdatabasedemo.*

class DeleteFragment : Fragment() {

    //private lateinit var deleteViewModel: DeleteViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        //deleteViewModel = ViewModelProviders.of(this).get(DeleteViewModel::class.java)
        //deleteViewModel = ViewModelProvider(this).get(DeleteViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_delete, container, false)
        val deleteButtons = root.findViewById<LinearLayout>(R.id.delete_buttons)

        getLists().forEachIndexed { _, listItem ->
            val newButton = Button(context)
            newButton.text = listItem.list.title
            newButton.setOnClickListener {
                AlertDialog.Builder(context)
                    .setTitle("Delete List")
                    .setMessage("Do you really want to delete list \"".plus(listItem.list.title).plus("\"?"))
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setPositiveButton("Delete") { _, _ ->
                        deleteList(listItem)
                        ListsToCompare.clear()
                        Navigation.findNavController(it).navigate(R.id.navigation_listcontainer)
                    }
                    .setNegativeButton("Cancel") { dialog, _ -> dialog.cancel() }
                    .show()
            }
            deleteButtons.addView(newButton)
        }
        return root
    }
}
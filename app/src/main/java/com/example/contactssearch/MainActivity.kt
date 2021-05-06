package com.example.contactssearch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    // declare DBHandler as mutable field using null safety
    var dbHandler: DBHandler? = null

    // declare RecyclerView as mutable field using null safety
    var contactRecyclerView: RecyclerView? = null

    // declare a StudentAdapter as a mutable field
    // specify that it will be initialized later
    lateinit var contactAdapter: ContactAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // fully initialize dbHandler
        dbHandler = DBHandler(this, null)

        // make RecyclerView refer to actual RecyclerView in activity_main layout resource
        contactRecyclerView = findViewById<View>(R.id.contactRecyclerView) as RecyclerView

        // initialize a MutableList of Students
        var contacts: MutableList<Contact> = ArrayList()

        // initialize the StudentAdapter
        contactAdapter = ContactAdapter(contacts)

        // tell Kotlin that RecyclerView isn't null and set the StudentAdapter on it
        contactRecyclerView!!.adapter = contactAdapter

        // tell Kotlin that the RecylerView isn't null and apply a LinearLayout to it
        contactRecyclerView!!.layoutManager = LinearLayoutManager(this)

        // populate the student table in the database
        // these lines of code should be commented out after the
        // app is run the first time
        addContact("Family1", "family1@chc.edu", "Family")
        addContact("Family2", "family2@chc.edu", "Family")
        addContact("Friend1", "friend1@chc.edu", "Friends")
        addContact("Friend2", "friend2@chc.edu", "Friends")
        addContact("Coworker1", "coworker1@chc.edu", "Coworkers")
        addContact("Coworker2", "coworker2@chc.edu", "Coworkers")
    }

    fun addContact(name: String, email: String, group: String) {
        dbHandler?.addContact(name, email, group)
    }

    fun search(view: View?){

        // get values input in EditTexts
        // store them in variables
        val group = groupEditText!!.text.toString()


        // trim variables and check if they are both equal to empty Strings
        if (major.trim() == "" && year.trim() == ""){
            // display "Please enter a search criteria! Toast
            Toast.makeText(this, "Please enter a search criteria!", Toast.LENGTH_LONG).show()
        } else if (major.trim() != "" && year.trim() != ""){
            // trim variables and check if neither are equal to an empty String
            // display "Please enter only one search criteria! Toast
            Toast.makeText(this, "Please enter only one search criteria!", Toast.LENGTH_LONG).show()
            // clear major EditText
            majorEditText!!.text.clear()
            // clear year EditText
            yearEditText!!.text.clear()
        } else {
            if (major.trim() != "") {
                // trim major and check if it's not equal to an empty String
                // call search by major in StudentAdapter
                studentAdapter.search(dbHandler!!, "major", major)
                // refresh StudentAdapter Mutable List
                studentAdapter.students = dbHandler!!.search("major", major)
                // clear major EditText
                majorEditText!!.text.clear()
            } else {
                // call search by year in StudentAdapter
                studentAdapter.search(dbHandler!!, "year", year)
                // refresh StudentAdapter Mutable List
                studentAdapter.students = dbHandler!!.search("year", year)
                // clear year EditText
                yearEditText!!.text.clear()
            }
        }
    }
}
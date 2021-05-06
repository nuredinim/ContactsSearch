package com.example.contactssearch

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ContactAdapter(
    // declare a MutableList of Students
    var contacts: MutableList<Contact>
) : RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {

    // declare a ViewHolder that will hold the layout of an item in
    // the RecyclerView
    class ContactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    // declare TextView immutable field using null safety
    var nameTextView: TextView? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ContactAdapter.ContactViewHolder {
        return  ContactViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.li_main,
                parent,
                false
            ))
    }

    override fun onBindViewHolder(holder: ContactAdapter.ContactViewHolder, position: Int) {
        // get current item in MutableList of Students and store it in
        // immutable variable
        val currentContact = contacts[position]

        holder.itemView.apply {
            // make TextView refer to TextView in li_main layout resource
            nameTextView = findViewById<View>(R.id.nameTextView) as TextView
            // assign the name value in the current item to text attribute of
            // TextView
            nameTextView!!.text = currentContact.name
        }
    }

    override fun getItemCount(): Int {
        return contacts.size
    }

    fun search(dbHandler: DBHandler, key: String, value: String) {
        dbHandler?.search(key, value)
        notifyDataSetChanged()
    }
}
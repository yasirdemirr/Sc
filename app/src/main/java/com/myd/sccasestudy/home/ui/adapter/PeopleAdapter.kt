package com.myd.sccasestudy.home.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.myd.sccasestudy.R
import com.myd.sccasestudy.core.source.Person
import com.myd.sccasestudy.databinding.ItemPersonBinding
import javax.inject.Inject

class PeopleAdapter @Inject constructor() :
    RecyclerView.Adapter<PeopleAdapter.PersonItemViewHolder>() {

    private var selectedPeople: MutableList<Person> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        PersonItemViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_person,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: PersonItemViewHolder, position: Int) {
        holder.bind(getPerson(position))
    }

    override fun getItemCount() = selectedPeople.size

    private fun getPerson(position: Int) = selectedPeople[position]

    fun setPeople(people: List<Person>) {
        val beforeSize = selectedPeople.size
        selectedPeople.addAll(people)
        notifyItemRangeInserted(beforeSize, people.size)
    }

    fun clearPeople() {
        selectedPeople.clear()
    }

    inner class PersonItemViewHolder(private val binding: ItemPersonBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(person: Person) {
            with(binding) {
                this.person = person
                executePendingBindings()
            }
        }
    }
}
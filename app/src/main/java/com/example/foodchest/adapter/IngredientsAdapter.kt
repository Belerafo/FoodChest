package com.example.foodchest.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.foodchest.R
import com.example.foodchest.databinding.IngredientsRowLayoutBinding
import com.example.foodchest.models.ExtendedIngredient
import com.example.foodchest.util.Constants.Companion.BASE_IMAGE_URL
import com.example.foodchest.util.RecipesDiffUtil
import java.util.*

class IngredientsAdapter: RecyclerView.Adapter<IngredientsAdapter.MyViewHolder>() {

    private var ingredientList = emptyList<ExtendedIngredient>()

    class MyViewHolder(val binding: IngredientsRowLayoutBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
      return MyViewHolder(IngredientsRowLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.ingredientImageView.load(BASE_IMAGE_URL + ingredientList[position].image){
            crossfade(600)
            error(R.drawable.ic_error_placeholder)
        }
        holder.binding.ingredientName.text = ingredientList[position].name.replaceFirstChar {
            if (it.isLowerCase()) it.titlecase(
                Locale.getDefault()
            ) else it.toString()
        }
        holder.binding.ingredientAmount.text = ingredientList[position].amount.toString()
        holder.binding.ingredientUnit.text = ingredientList[position].unit
        holder.binding.ingredientConsistency.text = ingredientList[position].consistency
        holder.binding.ingredientOriginal.text = ingredientList[position].original
    }

    override fun getItemCount(): Int {
        return ingredientList.size
    }

    fun setData(newIngredients: List<ExtendedIngredient>){
        val ingredientsDiffUtil =
            RecipesDiffUtil(ingredientList, newIngredients)
        val diffUtilResult = DiffUtil.calculateDiff(ingredientsDiffUtil)
        ingredientList = newIngredients
        diffUtilResult.dispatchUpdatesTo(this)
    }


}
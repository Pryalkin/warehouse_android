//package com.bsuir.kovko.app.screens.app.home
//
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.PopupMenu
//import androidx.recyclerview.widget.DiffUtil
//import androidx.recyclerview.widget.RecyclerView
//import com.bumptech.glide.Glide
//import java.util.Collections.emptyList
//
//
//interface AppActionListener {
//}
//
//class AppDiffCallback(
//    private val oldList: List<CarAnswerDTO>,
//    private val newList: List<CarAnswerDTO>,
//): DiffUtil.Callback() {
//
//    override fun getOldListSize(): Int = oldList.size
//
//    override fun getNewListSize(): Int = newList.size
//
//    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
//        val oldCar = oldList[oldItemPosition]
//        val newCar = newList[newItemPosition]
//        return oldCar.id == newCar.id
//    }
//
//    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
//        val oldCar = oldList[oldItemPosition]
//        val newCar = newList[newItemPosition]
//        return oldCar == newCar
//    }
//}
//
//class AppAdapter() : RecyclerView.Adapter<AppAdapter.AppViewHolder>(), View.OnClickListener {
//
//    class AppViewHolder(val binding: ItemAppBinding): RecyclerView.ViewHolder(binding.root)
//
//    var apps: List<CarAnswerDTO> = emptyList()
//        set(newValue) {
//            val diffCallback = AppDiffCallback(field, newValue)
//            val diffResult = DiffUtil.calculateDiff(diffCallback)
//            field = newValue
//            diffResult.dispatchUpdatesTo(this)
//        }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppViewHolder {
//        val inflater = LayoutInflater.from(parent.context)
//        val binding = ItemAppBinding.inflate(inflater, parent, false)
////        binding.root.setOnClickListener(this)
////        binding.moreImageViewButton.setOnClickListener(this)
//        return AppViewHolder(binding)
//    }
//
//    override fun onBindViewHolder(holder: AppViewHolder, position: Int) {
//        val car = apps[position]
//        val context = holder.itemView.context
//        with(holder.binding) {
//            holder.itemView.tag = car
//            moreImageViewButton.tag = car
//            brandTV.setText(car.brand)
//            volumeCapacityTV.setText("Объем двигателя: ${car.volumeCapacity}")
//            consumptionTV.setText("Расход: ${car.consumption} л/100 км")
//            numberOfSeatsTV.setText("Кол-во мест: ${car.numberOfSeats}")
//            priceTV.setText("Цена в сутки: ${car.price}$")
//            Glide.with(carImageView.context)
//                .load(car.image)
//                .placeholder(R.drawable.ic_image)
//                .error(R.drawable.ic_image)
//                .into(carImageView)
//        }
//    }
//
//    override fun getItemCount(): Int = apps.size
//
//    override fun onClick(v: View) {
//        val car = v.tag as CarAnswerDTO
//        when (v.id){
//        }
//    }
//
//    private fun showPopupMenu(v: View) {
//        val popupMenu = PopupMenu(v.context, v)
//        val context = v.context
//        val car = v.tag as CarAnswerDTO
//        popupMenu.setOnMenuItemClickListener{
//            when (it.itemId){
//
//            }
//            return@setOnMenuItemClickListener true
//        }
//        popupMenu.show()
//    }
//
//    companion object{
//
//    }
//
//}
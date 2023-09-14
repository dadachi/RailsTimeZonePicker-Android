package com.myturntag.railstimezonepickerandroid

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
  private var selectedTimeZone: String = TimeZones.defaultTimeZone

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    val spinnerTimeZone: Spinner = findViewById(R.id.spinner_time_zone)
    val timeZoneValues = TimeZones.map.values.toList()
    val currentTimeZoneValue = TimeZones.currentTimeZoneValue()
    val spinnerTimeZoneAdapter = ArrayAdapter(
      this,
      android.R.layout.simple_spinner_item,
      timeZoneValues
    )
    spinnerTimeZoneAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
    spinnerTimeZone.adapter = spinnerTimeZoneAdapter
    spinnerTimeZone.onItemSelectedListener = this
    val spinnerTimeZoneSelectedPosition = spinnerTimeZoneAdapter.getPosition(currentTimeZoneValue)
    spinnerTimeZone.setSelection(spinnerTimeZoneSelectedPosition)
 }

  /**
   * See [AdapterView.OnItemSelectedListener.onItemSelected]
   */
  override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
    when (parent.id) {
      R.id.spinner_time_zone -> {
        val timeZoneValue = parent.getItemAtPosition(position).toString()
        selectedTimeZone = TimeZones.keyFromValue(timeZoneValue)
      }
    }
  }

  /**
   * See [AdapterView.OnItemSelectedListener.onNothingSelected]
   */
  override fun onNothingSelected(parent: AdapterView<*>) {
  }
}
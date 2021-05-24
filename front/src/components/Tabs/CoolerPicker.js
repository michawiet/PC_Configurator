import { makeStyles } from '@material-ui/core'
import React from 'react'
import ProductPickerCard from '../products/VerticalProductCard'

function CoolerPicker() {
  const details = ["First", "Second", "Third", "Fourth"];

  return (
    <div>
      <ProductPickerCard productName={"Noctua NH-D15"} detail0={details[0]} detail1={details[1]} detail2={details[2]} detail3={details[3]} price={19999.99} />
    </div>
  )
}

export default CoolerPicker

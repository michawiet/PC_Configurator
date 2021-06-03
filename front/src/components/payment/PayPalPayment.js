import React, { useEffect, useRef } from 'react';
import SuccessPayment from './SuccessPayment'

function PayPalPayment(props) {
  const paypal = useRef()
  console.log(props.price);
  const [payd, setPayd] = React.useState(false);
  
  useEffect(() => {
      window.paypal
        .Buttons({
          createOrder: (data, actions, err) => {
            return actions.order.create({
              intent: "CAPTURE",
              purchase_units: [
                {
                  description: "",
                  amount: {
                    currency_code: "PLN",
                    value: props.price,
                  },
                },
              ],
            });
          },
          onApprove: async (data, actions) => {
            const order = await actions.order.capture();
            console.log(order);
            localStorage.setItem("cart", []);
            setPayd(true);
          },
          onError: (err) => {
            console.log(err);
          },
        })
        .render(paypal.current);
    }, []);

  return (
    <div>
      <div ref={paypal}></div>
      {payd ? <SuccessPayment/> : ""}
    </div>
  )
}

export default PayPalPayment


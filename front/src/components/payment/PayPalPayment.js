import React, { useEffect, useRef } from 'react';
import SuccessPayment from './SuccessPayment'
import { useAuth } from "../../AuthContext"


function PayPalPayment(props) {
  const paypal = useRef()
  const [payd, setPayd] = React.useState(false);
  const { currentUser } = useAuth();



  useEffect(() => {
      window.paypal
        .Buttons({
          createOrder: (data, actions, err) => {
            //tu order z cartu
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
            // a tu potwierdzenie zapłaty
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


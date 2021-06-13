import React, { useEffect, useRef, useState } from 'react';
import SuccessPayment from './SuccessPayment';
import { useAuth } from "../../AuthContext";
import axios from "axios";
import { useHistory } from "react-router-dom";

function PayPalPayment(props) {
  const paypal = useRef()
  const [payd, setPayd] = useState(false);
  const { currentUser } = useAuth();
  //const [orderId, setOrderId] = useState(0);
  let history = useHistory();
  var orderId = -1;
  //const baseUrl = "http://localhost:8080/cart/";
    
  useEffect(() => {
      window.paypal
        .Buttons({
          createOrder: async (data, actions, err) => {
            var totalPrice = 0;
            const res = await axios.post("http://localhost:8080/cart/createOrder?"
              + "email="
              + currentUser.email
            );
            console.log(res);
            totalPrice = res.data.totalPrice.toFixed(2);
            orderId = res.data.orderId;
            console.log("price after await axios post " + totalPrice);
            
            return actions.order.create({
              intent: "CAPTURE",
              purchase_units: [
                {
                  description: "",
                  amount: {
                    currency_code: "PLN",
                    value: totalPrice,
                  },
                },
              ],
            });
          },
          onApprove: async (data, actions) => {
            const order = await actions.order.capture();
            // a tu potwierdzenie zapłaty
            history.push({ pathname: "/zamówienie", orderId: orderId});
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
    </div>
  )
}

export default PayPalPayment


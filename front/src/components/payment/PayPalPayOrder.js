import React, { useEffect, useRef, useState } from 'react';
import { useAuth } from "../../AuthContext";
import axios from "axios";
import { useHistory } from "react-router-dom";

function PayPalPayment(props) {
  const paypal = useRef()
  const { currentUser } = useAuth();
  let history = useHistory();
  var orderId = -1;

  useEffect(() => {
      window.paypal
        .Buttons({
          createOrder: async (data, actions, err) => {
            var totalPrice = 0;
            totalPrice = props.totalPrice ;
            orderId = props.orderId ;
            return actions.order.create({
              intent: "CAPTURE",
              purchase_units: [
                {
                  description: "Zamówienie #" + orderId,
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
            currentUser.getIdToken(/* forceRefresh */ true).then(async function(idToken) {
              await axios.post("http://localhost:8080/orders/confirmOrder?" 
                  + "token="
                  + idToken
                  + "&orderId=" 
                  + orderId);
                history.push({ pathname: "/zamówienie", orderId: orderId});
            }).catch(function(error) {
              // Handle error
            });
          },
          onError: (err) => {
            console.log(err);
          },
          onCancel: () => {
            console.log("zamkniete");
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


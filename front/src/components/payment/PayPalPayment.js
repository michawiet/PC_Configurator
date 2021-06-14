import React, { useEffect, useRef } from 'react';
import { useAuth } from "../../AuthContext";
import axios from "axios";
import { useHistory } from "react-router-dom";

function PayPalPayment(props) {
  const paypal = useRef();
  const { currentUser } = useAuth();
  let history = useHistory();
  var orderId = -1;

  useEffect(() => {
      window.paypal
        .Buttons({
          createOrder: async (data, actions, err) => {
            const token = await currentUser.getIdToken(/* forceRefresh */ true);
            const res = await axios.post("http://localhost:8080/cart/createOrder?"
              + "token="
              + token
            );
            orderId = res.data.orderId;
            return actions.order.create({
              intent: "CAPTURE",
              purchase_units: [
                {
                  description: "Zamówienie #" + orderId,
                  amount: {
                    currency_code: "PLN",
                    value: res.data.totalPrice.toFixed(2),
                  },
                },
              ],
            });
          },
          onApprove: async (data, actions) => {
            const order = await actions.order.capture();
            props.setProducts([]);
            props.setProductCount(0);
            props.setTotalPrice(0);
            const token = await currentUser.getIdToken(/* forceRefresh */ true);
            await axios.post("http://localhost:8080/orders/confirmOrder?" 
              + "token="
              + token
              + "&orderId=" 
              + orderId);
            history.push({ pathname: "/zamówienie", orderId: orderId});
          },
          onError: (err) => {
            console.log(err);
          },
          onCancel: async () => {
            const token = await currentUser.getIdToken(/* forceRefresh */ true);
            axios.post("http://localhost:8080/orders/cancelOrder?" 
              + "token="
              + token
              + "&orderId=" 
              + orderId
            ).catch(() => {
                console.log("exception in post method");
            });
            history.push("/");
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


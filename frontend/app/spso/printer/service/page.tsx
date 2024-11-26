'use client';

import getPrinterOrder, { Order } from "@/lib/printer-order";
import { redirect, useSearchParams } from "next/navigation";
import { useEffect, useState } from "react";

export default function ServicePage() {
    const [orders, setOrders] = useState<Order[]>([]);
    const params = useSearchParams();
    const printerId = params.get("printerId");

    if (!printerId) {
        redirect("/spso/printer");
    }

    useEffect(() => {
        setInterval(() => {
            getPrinterOrder(printerId)
                .then(setOrders)
                .catch(() => {
                    redirect("/spso/printer?error=404");
                });
        }, 5000);
    }, [printerId]);

    return (
        <div>
            {
                orders.map((order) => (
                    <div key={order.orderId}>
                        {order.documentUrl}
                    </div>
                ))
            }
        </div>
    );
}
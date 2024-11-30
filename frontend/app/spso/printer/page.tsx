'use client';
import ServicePage from "./service/manage";
import OrderPage from "./order/page";
import AddPrinter from "./service/add";
import { useState } from "react";
export default function PrinterPage() {
    const [showAddPrinter, setShowAddPrinter] = useState(false);

    const handleAddPrinterClick = () => {
        setShowAddPrinter(true);  // Set the state to true when the button is clicked
    };

    const [printerId, setPrinterId] = useState('');
    return ( 
        <div>
            {(showAddPrinter) ? (
                <AddPrinter 
                    showAddPrinter={showAddPrinter} 
                    setShowAddPrinter={setShowAddPrinter} 
                />  // Render AddPrinter if showAddPrinter is true
            ) : (
                <ServicePage onAddPrinterClick={handleAddPrinterClick}/>  // Pass the handler to ServicePage
            )}
        </div>
    );
}
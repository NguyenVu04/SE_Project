'use client';
import ServicePage from "./service/manage";
import AddPrinter from "./service/add";
import { useEffect, useState } from "react";
import getUserId from "@/lib/user-id";
import { redirect } from "next/navigation";
import Image from "next/image";
export default function PrinterPage() {
    const [id, setId] = useState<string | null>(null);

    useEffect(() => {
        getUserId("spso")
            .then((id) => {
                setId(id);
            })
            .catch(() => {
                redirect("/");
            })
    }, []);

    const [showAddPrinter, setShowAddPrinter] = useState(false);

    const handleAddPrinterClick = () => {
        setShowAddPrinter(true);  // Set the state to true when the button is clicked
    };

    if (!id) {
        return (
            <div className="flex w-screen h-screen justify-center items-center">
                <Image src="/hcmut.svg" alt="hcmut" width={64} height={64} />
            </div>
        )
    }

    return ( 
        <div>
            {(showAddPrinter) ? (
                <AddPrinter 
                    showAddPrinter={showAddPrinter} 
                    setShowAddPrinter={setShowAddPrinter} 
                /> 
            ) : (
                <ServicePage onAddPrinterClick={handleAddPrinterClick}/>  // Pass the handler to ServicePage
            )}
        </div>
    );
}
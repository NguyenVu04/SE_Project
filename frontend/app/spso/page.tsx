'use client';
import getUserId from "@/lib/user-id";
import TopBar from "../../components/ui/topbar";
import { FiPrinter } from "react-icons/fi";
import { FiBook } from "react-icons/fi";
import { MdHistory } from "react-icons/md";
import { redirect } from 'next/navigation'
import { SiMicrosoftexcel } from "react-icons/si";
import { useEffect, useState } from "react";
import Image from "next/image";
export default function SPSOPage() {
    const [id, setId] = useState<string | null>(null);
    useEffect(() => {
        getUserId("spso")
            .then((id) => {
                setId(id);
            })
            .catch(() => {
                redirect("/");
            })
    })

    const handlePrinter = () => {
        redirect('/spso/printer');
    }
    const handlePolicy = () => {
        redirect('/spso/policy');
    }
    const handleHistory = () => {
        redirect('/spso/history');
    }
    const handleReport = () => {
        redirect('/spso/report');
    }
    
    if (!id) {
        return (
            <div className="flex w-screen h-screen justify-center items-center">
                <Image src="/hcmut.svg" alt="hcmut" width={64} height={64} />
            </div>
        )
    }

    return (
        <div className="flex flex-col min-h-screen">
            <TopBar />
            <div
                id="main-content"
                className="flex-grow flex flex-col items-center justify-center text-white bg-gradient-to-b from-[#0381FF] to-[#02067A]"
            >
                <h1 className="text-6xl text-orange-600">Welcome to SPSS</h1>
                <p>
                    where quality printing meets exceptional service – bringing your ideas to life, one print at a time!
                </p>
            </div>
            <div className="flex justify-center px-4 py-2 bg-gray-800" id="footer">
                <button className="h-40 bg-purple-600 rounded-xl text-white text-xl mx-4 my-2 flex flex-col items-center justify-center p-2"
                    onClick={handlePrinter}    >
                    Quản lý máy in
                    <FiPrinter size={30} className="mt-4" />
                </button>
                <button className="h-40 bg-purple-600 rounded-xl text-white text-xl mx-4 my-2 flex flex-col items-center justify-center p-2"
                    onClick={handlePolicy}    >
                    Quản lý chính sách
                    <FiBook size={30} className="mt-4" />
                </button>
                <button className="h-40 bg-purple-600 rounded-xl text-white text-xl mx-4 my-2 flex flex-col items-center justify-center p-2"
                    onClick={handleHistory}    >
                    Quản lý lịch sử in
                    <MdHistory size={35} className="mt-4" />
                </button>
                <button className="h-40 bg-purple-600 rounded-xl text-white text-xl mx-4 my-2 flex flex-col items-center justify-center p-2"
                    onClick={handleReport}    >
                    Xem báo cáo
                    <SiMicrosoftexcel size={35} className="mt-4" />
                </button>
            </div>
        </div>

    );
}
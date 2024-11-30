// 'use server';
'use client';
// import getUserId from "@/lib/user-id";
import TopBar from "../../components/ui/topbar";
import { Button } from "@/components/ui/button";
import { FiPrinter } from "react-icons/fi";
import { FiBook } from "react-icons/fi";
import { redirect } from 'next/navigation'
export default function SPSOPage() {
    // const id = await getUserId("spso");
    
    const handlePrinter = () => {
        redirect('/spso/printer');
    }
    const handlePolicy = () => {
        redirect('/spso/policy');
    }
    return (
        <div className="flex flex-col min-h-screen">
            <TopBar />
            <div
                id="main-content"
                className="flex-grow flex flex-col items-center justify-center text-white bg-gradient-to-b from-[#0381FF] to-[#02067A]"
            >
                <h1 className="text-6xl text-orange-600">Welcome to SPSO page</h1>
                <p>
                    where quality printing meets exceptional service – bringing your ideas to life, one print at a time!
                </p>
            </div>
            <div className="flex justify-center px-4 py-2 bg-gray-800" id="footer">
                <Button className="h-40 bg-purple-600 rounded-xl text-white text-xl mx-4 my-2 flex flex-col items-center justify-center"
                    onClick={handlePrinter}    >
                    <FiPrinter className="text-6xl mb-2"/>
                    Quản lý máy in
                </Button>
                <Button className="h-40 bg-purple-600 rounded-xl text-white text-xl mx-4 my-2 flex flex-col items-center justify-center"
                    onClick={handlePolicy}    >
                    <FiBook className="text-5xl mb-2" />
                    Quản lý chính sách
                </Button>
            </div>
        </div>

    );
}
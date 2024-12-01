'use client';
import React from "react";
import TopBar from "../../../../components/ui/topbar";
import { Alert } from '@/components/ui/alert'
import { Button } from '@/components/ui/button'
import { Input } from '@/components/ui/input'
import { Label } from '@/components/ui/label'
import createPrinter from "@/lib/add-printer";
import { useSearchParams } from "next/navigation";

interface AddPrinterProps {
    showAddPrinter: boolean;
    setShowAddPrinter: (value: boolean) => void;
}

const AddPrinter: React.FC<AddPrinterProps> = ({ showAddPrinter, setShowAddPrinter }) => {
    const params = useSearchParams();
    const error = params?.get("error");
    const handleCancel = () => {
        setShowAddPrinter(false); // Update the state in the parent
    };
    return (
        <div className="flex flex-col min-h-screen">
            <TopBar></TopBar>
            {/* main content */}
            <div
                id="main-content"
                className="flex-grow flex flex-col items-center justify-center text-black bg-gradient-to-b from-[#0381FF] to-[#02067A]"
            >
                {/* manage printer */}
                <div className="bg-white p-6 rounded-lg shadow-lg max-w-5xl w-full mt-3 mb-3 justify-center items-center">
                    <h1 className="text-center text-4xl">THÊM MÁY IN</h1>
                    <hr className="h-1 mx-auto bg-black border-0 rounded md:my-5 dark:bg-gray-700 mb-5" />
                    <form action={createPrinter} className="space-y-12">
                        <div className="grid w-full items-center gap-1.5">
                            <Label htmlFor="manufacturer">Nhà sản xuất</Label>
                            <Input
                                className="w-full"
                                required
                                id="manufacturer"
                                type="text"
                                name="manufacturer"
                            />
                        </div>
                        <div className="grid w-full items-center gap-1.5">
                            <Label htmlFor="model">Mẫu máy in</Label>
                            <Input
                                className="w-full"
                                required
                                id="model"
                                type="text"
                                name="model"
                            />
                        </div>
                        <div className="grid w-full items-center gap-1.5">
                            <Label htmlFor="campusName">Tên trường</Label>
                            <Input
                                className="w-full"
                                required
                                id="campusName"
                                type="text"
                                name="campusName"
                            />
                        </div>
                        {/* Toàn nhà and Số phòng side by side */}
                        <div className="grid grid-cols-2 gap-6">
                            <div className="grid items-center gap-1.5">
                                <Label htmlFor="buildingName">Toà nhà</Label>
                                <Input
                                    className="w-full"
                                    required
                                    id="buildingName"
                                    type="text"
                                    name="buildingName"
                                />
                            </div>
                            <div className="grid items-center gap-1.5">
                                <Label htmlFor="roomNumber">Số phòng</Label>
                                <Input
                                    className="w-full"
                                    required
                                    id="roomNumber"
                                    type="text"
                                    name="roomNumber"
                                />
                            </div>
                        </div>
                        <div className="grid w-full items-center gap-1.5">
                            <Label htmlFor="description">Mô tả</Label>
                            <Input
                                className="w-full h-20"
                                required
                                id="description"
                                type="text"
                                name="description"
                            />
                        </div>
                        {error && <Alert>{error}</Alert>}
                        <div className="w-full">
                            <Button type="submit" className="w-full bg-hcmut-dark" size="lg">
                                Hoàn thành
                            </Button>
                            <Button type="submit" className="w-full bg-hcmut-dark mt-4" size="lg" onClick={handleCancel}>Quay lại</Button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    );
}
export default AddPrinter;
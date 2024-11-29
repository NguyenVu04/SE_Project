'use client';
import React from "react";
import TopBar from "../../../../components/ui/topbar";
import { Alert } from '@/components/ui/alert'
import { Button } from '@/components/ui/button'
import { Input } from '@/components/ui/input'
import { Label } from '@/components/ui/label'
export default function AddPrinter() {
    const [manufacturer, setManufacturer] = React.useState('');
    const [model, setModel] = React.useState('');
    const [schoolname, setSchoolname] = React.useState('');
    const [building, setBuilding] = React.useState('');
    const [room, setRoom] = React.useState('');
    const [description, setDescription] = React.useState('');
    const [error, setError] = React.useState('');
    
    const onSubmit = () => {

    }
    
      return (
        <div className="flex flex-col min-h-screen">
            <TopBar></TopBar>
            {/* main content */}
            <div
                style={{ background: 'linear-gradient(to bottom, #0381FF, #02067A)' }}
                id="main-content"
                className="flex-grow flex flex-col items-center justify-center text-black"
            >
            {/* manage printer */}
                <div className="bg-white p-6 rounded-lg shadow-lg max-w-5xl w-full mt-3 mb-3 justify-center items-center">
                    <h1 className="text-center text-4xl">THÊM MÁY IN</h1>
                    <hr className="h-1 mx-auto bg-black border-0 rounded md:my-5 dark:bg-gray-700 mb-5"/>
                    <form onSubmit={onSubmit} className="space-y-12">
                        <div className="grid w-full items-center gap-1.5">
                            <Label htmlFor="manufacture">Nhà sản xuất</Label>
                            <Input
                                className="w-full"
                                required
                                value={manufacturer}
                                onChange={(e) => setManufacturer(e.target.value)}
                                id="manufacture"
                                type="text"
                            />
                        </div>
                        <div className="grid w-full items-center gap-1.5">
                            <Label htmlFor="model">Mẫu máy in</Label>
                            <Input
                                className="w-full"
                                required
                                value={model}
                                onChange={(e) => setModel(e.target.value)}
                                id="model"
                                type="text"
                            />
                        </div>
                        <div className="grid w-full items-center gap-1.5">
                            <Label htmlFor="schoolname">Tên trường</Label>
                            <Input
                                className="w-full"
                                required
                                value={schoolname}
                                onChange={(e) => setSchoolname(e.target.value)}
                                id="schoolname"
                                type="text"
                            />
                        </div>
                        {/* Toàn nhà and Số phòng side by side */}
                        <div className="grid grid-cols-2 gap-6">
                            <div className="grid items-center gap-1.5">
                                <Label htmlFor="building">Toà nhà</Label>
                                <Input
                                    className="w-full"
                                    required
                                    value={building}
                                    onChange={(e) => setBuilding(e.target.value)}
                                    id="building"
                                    type="text"
                                />
                            </div>
                            <div className="grid items-center gap-1.5">
                                <Label htmlFor="room">Số phòng</Label>
                                <Input
                                    className="w-full"
                                    required
                                    value={room}
                                    onChange={(e) => setRoom(e.target.value)}
                                    id="room"
                                    type="text"
                                />
                            </div>
                        </div>
                        <div className="grid w-full items-center gap-1.5">
                            <Label htmlFor="description">Mô tả</Label>
                            <Input
                                className="w-full h-20"
                                required
                                value={description}
                                onChange={(e) => setDescription(e.target.value)}
                                id="description"
                                type="text"
                            />
                        </div>
                        {error && <Alert>{error}</Alert>}
                        <div className="w-full">
                            <Button className="w-full bg-hcmut-dark" size="lg">
                                Hoàn thành
                            </Button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
      );
}
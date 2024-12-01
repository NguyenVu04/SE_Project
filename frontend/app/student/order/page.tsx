'use client';
import Order from '@/components/Order'
import React from 'react'
import { useState, useEffect } from 'react'

const Page = () => {
    const [studentId, setStudentId] = useState<string>("");
    useEffect(() => {
        setStudentId("1");
    }, []);
    // hardcode order
    
    return (
        <div 
        className="bg-gray-100 w-screen min-h-screen flex flex-col bg-gradient-to-b from-[#0381FF] to-[#02067A]">
            <Order studentId={studentId}></Order>
        </div>
    )
}

export default Page
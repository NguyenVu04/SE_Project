import React from 'react'
import Link from 'next/link'
import { FiCheckCircle } from "react-icons/fi";

const Success = () => {
  return (
    <div
      
      style={{ position: 'absolute', top: 70, left: 150, right: 150, bottom: 30 }}
      className="bg-white flex flex-col items-center justify-between py-5 px-20" // Sử dụng justify-center để căn giữa
    >
        <div></div>
        <div className="flex flex-col items-center justify-between space-y-8">
            <FiCheckCircle className='text-7xl' />
            <h1 
            style={{
                color: '#109F00'
            }}
            className="text-3xl mb-4">
                TÀI LIỆU CỦA BẠN ĐÃ ĐƯỢC IN THÀNH CÔNG!!!
            </h1>
        </div>
        <Link href="/student/success">
            <button
                style = {{background: '#EC221F', width: '100px'}}
                className=" text-white p-4 rounded-lg"
            >
                Quay lại
            </button>
        </Link>
    </div>
  )
}

export default Success
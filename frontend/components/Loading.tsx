import React from 'react'
import { FaRegBell } from "react-icons/fa"
import Link from 'next/link'

const Loading = () => {
  return (
    <div
      style={{ position: 'absolute', top: 70, left: 150, right: 150, bottom: 30 }}
      className="bg-white flex flex-col items-center justify-between py-5 px-20" // Sử dụng justify-center để căn giữa
    >
        <div></div>
        <div className="flex flex-col items-center justify-between space-y-8">
            <FaRegBell className='text-7xl' />
            <h1 
            style={{
                color: '#109F00'
            }}
            className="text-3xl">
                GỬI YÊU CẦU THÀNH CÔNG !!!
            </h1>
            <h1 className='text-3xl'>
            TÀI LIỆU CỦA BẠN ĐANG ĐƯỢC IN
            </h1>
        </div>
        <Link href="/student">
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

export default Loading
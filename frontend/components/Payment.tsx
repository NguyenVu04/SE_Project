import React from 'react'
import Link from 'next/link';

const Payment = () => {
  return (
    <div
      style={{ position: 'absolute', top: 70, left: 150, right: 150, bottom: 30 }}
      className="bg-white flex flex-col items-center py-5 px-20" // Sử dụng justify-center để căn giữa
    >
        <h2 className="text-3xl mt-2">MUA THÊM TRANG</h2>
        <hr className="border-b border-black w-full mt-2" />
        <div 
            className='m-32'
            style = {{width: '720px', height: '100%'}}>
            <div className="flex flex-col justify-between py-5 px-20 h-full ">
                <div>
                    <p className='mb-2'>Số trang cần mua:</p>
                    <input 
                        type="number" 
                        id="numberOfPages" 
                        placeholder="0" 
                        style={{ width: '100%', padding: '10px', border: '2px solid #007bff', borderRadius: '4px', fontSize: '16px', outline: 'none' }} 
                    />
                </div>
                <p className='text-right'>Tổng số tiền: 0đ</p>
                <div className="flex flex-row justify-between">
                    <Link href="/student/document">
                        <button 
                            style = {{background: '#08FF3D', width: '150px'}}
                            className="p-4 rounded-full shadow-md">
                        Quay lại
                        </button>
                    </Link>
                    <Link href="/student/document">
                        <button 
                            style = {{background: '#EC221F', width: '150px'}}
                            className="text-white p-4 w-10 rounded-full shadow-md">
                        Thanh toán
                        </button>
                    </Link>
                </div>
            </div>
        </div>
    </div>
  )
}

export default Payment
'use client';
import React, { useState } from 'react'
import Link from 'next/link';
import payByMomo from '@/lib/momo';
import { redirect } from 'next/navigation';
import updateBalance from '@/lib/update-balance';

const Payment = ({ studentId }: { studentId: string }) => {
    const [numberOfPages, setNumberOfPages] = useState<number>(0);
    return (
        <div
            className="bg-white flex flex-col items-center py-5 px-20 absolute top-[70px] left-[150px] right-[150px] bottom-[30px]" // Sử dụng justify-center để căn giữa
        >
            <h2 className="text-3xl mt-2">MUA THÊM TRANG</h2>
            <hr className="border-b border-black w-full mt-2" />
            <div
                className='m-32 w-[720px] h-full'>
                <div className="flex flex-col justify-between py-5 px-20 h-full ">
                    <div>
                        <p className='mb-2'>Số trang cần mua:</p>
                        <input
                            onChange={(e) => setNumberOfPages(e.currentTarget.valueAsNumber)}
                            type="number"
                            id="numberOfPages"
                            placeholder="0"
                            className='w-full p-[10px] border-[2px] border-[#007bff] rounded-[4px] text-[16px] outline-none'
                        />
                    </div>
                    <p className='text-right'>Tổng số tiền: {numberOfPages * 500}đ</p>
                    <div className="flex flex-row justify-between">
                        <Link href="/student/document">
                            <button
                                type='button'
                                className="p-4 rounded-full shadow-md bg-[#08FF3D] w-[150px]">
                                Quay lại
                            </button>
                        </Link>
                        <button
                            type='button'
                            onClick={async () => {
                                if (numberOfPages === 0) return;
                                const res = await payByMomo(numberOfPages * 500);
                                const status = await updateBalance(studentId, numberOfPages);
                                if (status !== 200) {
                                    alert('Có lỗi xảy ra! Vui lòng thử lại');
                                }
                                setNumberOfPages(0);
                                redirect(res.payUrl);
                            }}
                            className="text-white p-4 w-[150px] rounded-full shadow-md bg-[#EC221F]">
                            Thanh toán
                        </button>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default Payment
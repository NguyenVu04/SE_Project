import React from 'react'
import Link from 'next/link'
import { FiPrinter } from "react-icons/fi";

const Printer = () => {
  return (
    <div
      className="bg-white flex flex-col justify-between py-5 px-20 absolute top-[70px] left-[150px] right-[150px] bottom-[30px]" // Sử dụng justify-center để căn giữa
    >
        <div className='flex flex-col items-center w-full'>
            <h2 className="text-3xl mt-2">CHỌN MÁY IN</h2>
            <hr className="border-b border-black w-full mb-10 mt-2" />
            <div className="flex flex-col space-y-10 w-full">
                <div className="flex flex-row space-x-10 w-full">
                    <div className="flex flex-row space-x-4 w-1/2">
                        <div className="flex flex-col items-center">
                            <FiPrinter className='text-6xl mb-3'/>
                            <Link href="/student/loading">
                                <button
                                    type='button'
                                    className="p-1 rounded-lg border-black border-2 bg-[#08FF3D] w-[60px]"
                                >
                                    <span className="text-sm">Chọn</span>
                                </button>
                            </Link>
                        </div>
                        <ul className="list-disc pl-6">
                            <li className="text-sm">Nhà sản xuất: HP</li>
                            <li className="text-sm">Mẫu máy in: OfficeJet Pro 8135e</li>
                            <li className="text-sm">Tên trường: Đại học Bách Khoa TP.HCM</li>
                            <li className="text-sm">Tòa: H6</li>
                            <li className="text-sm">Số phòng: 612</li>
                        </ul>
                    </div>
                    <div className="flex flex-row space-x-4 w-1/2">
                        <div className="flex flex-col items-center">
                            <FiPrinter className='text-6xl mb-3'/>
                            <Link href="/student/loading">
                                <button
                                    type='button'
                                    className="p-1 rounded-lg border-black border-2 bg-[#08FF3D] w-[60px]"
                                >
                                    <span className="text-sm">Chọn</span>
                                </button>
                            </Link>
                        </div>
                        <ul className="list-disc pl-6">
                            <li className="text-sm">Nhà sản xuất: HP</li>
                            <li className="text-sm">Mẫu máy in: OfficeJet Pro 8135e</li>
                            <li className="text-sm">Tên trường: Đại học Bách Khoa TP.HCM</li>
                            <li className="text-sm">Tòa: H6</li>
                            <li className="text-sm">Số phòng: 612</li>
                        </ul>
                    </div>
                </div>

                <div className="flex flex-row space-x-10 w-full">
                    <div className="flex flex-row space-x-4 w-1/2">
                        <div className="flex flex-col items-center">
                            <FiPrinter className='text-6xl mb-3'/>
                            <Link href="/student/loading">
                                <button
                                    type='button'
                                    className="p-1 rounded-lg border-black border-2 bg-[#08FF3D] w-[60px]"
                                >
                                    <span className="text-sm">Chọn</span>
                                </button>
                            </Link>
                        </div>
                        <ul className="list-disc pl-6">
                            <li className="text-sm">Nhà sản xuất: HP</li>
                            <li className="text-sm">Mẫu máy in: OfficeJet Pro 8135e</li>
                            <li className="text-sm">Tên trường: Đại học Bách Khoa TP.HCM</li>
                            <li className="text-sm">Tòa: H6</li>
                            <li className="text-sm">Số phòng: 612</li>
                        </ul>
                    </div>
                    <div className="flex flex-row space-x-4 w-1/2">
                        <div className="flex flex-col items-center">
                            <FiPrinter className='text-6xl mb-3'/>
                            <Link href="/student/loading">
                                <button
                                    type='button'
                                    className="p-1 rounded-lg border-black border-2 bg-[#08FF3D] w-[60px]"
                                >
                                    <span className="text-sm">Chọn</span>
                                </button>
                            </Link>
                        </div>
                        <ul className="list-disc pl-6">
                            <li className="text-sm">Nhà sản xuất: HP</li>
                            <li className="text-sm">Mẫu máy in: OfficeJet Pro 8135e</li>
                            <li className="text-sm">Tên trường: Đại học Bách Khoa TP.HCM</li>
                            <li className="text-sm">Tòa: H6</li>
                            <li className="text-sm">Số phòng: 612</li>
                        </ul>
                    </div>
                </div>

                <div className="flex flex-row space-x-10 w-full">
                    <div className="flex flex-row space-x-4 w-1/2">
                        <div className="flex flex-col items-center">
                            <FiPrinter className='text-6xl mb-3'/>
                            <Link href="/student/loading">
                                <button
                                    type='button'
                                    className="p-1 rounded-lg border-black border-2 bg-[#08FF3D] w-[60px]"
                                >
                                    <span className="text-sm">Chọn</span>
                                </button>
                            </Link>
                        </div>
                        <ul className="list-disc pl-6">
                            <li className="text-sm">Nhà sản xuất: HP</li>
                            <li className="text-sm">Mẫu máy in: OfficeJet Pro 8135e</li>
                            <li className="text-sm">Tên trường: Đại học Bách Khoa TP.HCM</li>
                            <li className="text-sm">Tòa: H6</li>
                            <li className="text-sm">Số phòng: 612</li>
                        </ul>
                    </div>
                    <div className="flex flex-row space-x-4 w-1/2">
                        <div className="flex flex-col items-center">
                            <FiPrinter className='text-6xl mb-3'/>
                            <Link href="/student/loading">
                                <button
                                    type='button'
                                    className="p-1 rounded-lg border-black border-2 bg-[#08FF3D] w-[60px]"
                                >
                                    <span className="text-sm">Chọn</span>
                                </button>
                            </Link>
                        </div>
                        <ul className="list-disc pl-6">
                            <li className="text-sm">Nhà sản xuất: HP</li>
                            <li className="text-sm">Mẫu máy in: OfficeJet Pro 8135e</li>
                            <li className="text-sm">Tên trường: Đại học Bách Khoa TP.HCM</li>
                            <li className="text-sm">Tòa: H6</li>
                            <li className="text-sm">Số phòng: 612</li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        <Link href="/student/student">
            <button
                type='button'
                className="text-white p-4 rounded-lg bg-[#EC221F] w-[100px]"
            >
                Quay lại
            </button>
        </Link>
    </div>
  )
}

export default Printer
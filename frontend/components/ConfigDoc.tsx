'use client';
import React, { useEffect, useState } from 'react'
import Link from 'next/link';
import { redirect, RedirectType, useSearchParams } from 'next/navigation';
import { OrderInfo } from '@/app/student/configdoc/page';
import { FiPrinter } from 'react-icons/fi';
import { Printer } from '@/app/spso/printer/service/manage';
import getActivePrinter from '@/lib/get-active-printer';
import addOrder from '@/lib/add-order';

const parseRange = (str: string) => {
    return str.split(',').flatMap(part => {
        if (part.includes('-')) {
            const [start, end] = part.split('-').map(Number);
            return Array.from({ length: end - start + 1 }, (_, i) => start + i);
        }
        return Number(part);
    });
};

const ConfigDoc = (
    { studentId }: { studentId: string }) => {
    const params = useSearchParams();
    if (!params.get('fileName')) {
        redirect('/student/upload');
    }

    const [activePrinter, setActivePrinter] = useState<Printer[]>([]);
    const [order, setOrder] = useState<OrderInfo | null>(null);

    useEffect(() => {
        getActivePrinter().then((printers) => {
            setActivePrinter(printers);
        }).catch(() => {
            alert('Có lỗi xảy ra! Vui lòng thử lại');
        })
    }, []);

    const [paperSize, setPaperSize] = useState<string>('A3');
    const [singleSided, setSingleSided] = useState<boolean>(true);
    const [numberOfCopies, setNumberOfCopies] = useState<number>(1);
    const [pageNumbers, setPageNumbers] = useState<number[]>([]);


    const fileName = params.get('fileName');
    return (
        <div className="bg-white flex flex-col items-center py-5 px-20 absolute top-[70px] left-[150px] right-[150px] bottom-[30px]">
            {
                !order ? <>
                    <h2 className="text-3xl mt-2">CHỌN THÔNG SỐ IN</h2>
                    <hr className="border-b border-black w-full mb-20 mt-2" />
                    <div className='flex flex-col justify-between items-center h-full w-full'>
                        <div className="flex flex-row justify-start space-x-32 w-full">
                            {/* Container cho hình ảnh và thông tin tài liệu */}
                            <div className="flex flex-col items-center">
                                <div className="bg-gray-300 w-48 h-64 mb-4"></div> {/* Placeholder cho hình ảnh */}
                                <span className="text-xl mb-4">{fileName}</span>
                            </div>
                            <div className="flex flex-col space-y-8">
                                <label className="flex items-center space-x-4">
                                    <span className="text-2xl">Trang được chọn:</span>
                                    <input required type="text" placeholder="e.g. 1-5, 8, 11-13" className="p-2 border border-gray-300 rounded"
                                        onChange={(e) => setPageNumbers(parseRange(e.target.value))} />
                                </label>

                                <label className="flex items-center space-x-4">
                                    <input type="checkbox" className="w-6 h-6"
                                        onChange={(e) => setSingleSided(!e.target.checked)} />
                                    <span className="text-2xl">In hai mặt</span>
                                </label>

                                <label className="flex items-center space-x-4">
                                    <span className="text-2xl">Cỡ giấy:</span>

                                    <select defaultValue={"A3"} className="p-2 w-28 border border-gray-300 rounded" onChange={(e) => setPaperSize(e.target.value)}>
                                        <option value="A3">A3</option>
                                        <option value="A4">A4</option>
                                        <option value="A5">A5</option>
                                        <option value="A6">A6</option>
                                        <option value="Letter">Letter</option>
                                    </select>
                                </label>

                                <label className="flex items-center space-x-4">
                                    <span className="text-2xl">Số bản in:</span>
                                    <input
                                        required
                                        type="number"
                                        defaultValue={1}
                                        min="1"
                                        className="p-2 w-28 border border-gray-300 rounded"
                                        onChange={(e) => setNumberOfCopies(parseInt(e.target.value))}
                                    />
                                </label>

                            </div>
                        </div>
                        {/* Buttons */}
                        <div className="flex space-x-4 w-full justify-between">
                            <button
                                type='button'
                                className="text-white p-4 rounded-lg w-[100px] bg-[#EC221F] hover:bg-blue-600"
                                onClick={() => redirect('/student/document')}
                            >
                                Quay lại
                            </button>

                            <button
                                type='button'
                                className="p-4 rounded-lg border-black border-2 bg-[#08FF3D] w-[100px] hover:bg-blue-300"
                                onClick={() => {
                                    if (pageNumbers.length === 0) {
                                        alert('Vui lòng chọn trang');
                                        return;
                                    }
                                    setOrder({
                                        filename: fileName as string,
                                        paperSize: paperSize,
                                        singleSided: singleSided,
                                        numberOfCopies: numberOfCopies,
                                        pageNumbers: pageNumbers
                                    });
                                }}
                            >
                                Tiếp tục
                            </button>
                        </div>
                    </div>
                </> : <>
                    <div className='flex flex-col items-center w-full'>
                        <h2 className="text-3xl mt-2">CHỌN MÁY IN</h2>
                        <hr className="border-b border-black w-full mb-10 mt-2" />
                        <div className="flex flex-row flex-wrap justify-around w-full">
                            {
                                activePrinter.map((printer) =>
                                    <div key={printer.id} className="flex flex-row space-x-4 w-2/5 py-4">
                                        <div className="flex flex-col items-center">
                                            <FiPrinter className='text-6xl mb-3' />
                                            <Link href="/student/loading">
                                                <button
                                                    type='button'
                                                    className="p-1 rounded-lg border-black border-2 bg-[#08FF3D] w-[60px]"
                                                    onClick={async () => {
                                                        // studentId = "674ac231b537070a6cae8bad";
                                                        const status = await addOrder(studentId, printer.id, order);
                                                        if (status !== 200) {
                                                            alert('Có lỗi xảy ra! Vui lòng thử lại')
                                                            redirect('/student/document', RedirectType.replace);
                                                        } else {
                                                            redirect('/student/loading', RedirectType.replace);
                                                        }
                                                    }}  
                                                >
                                                    <span className="text-sm">Chọn</span>
                                                </button>
                                            </Link>
                                        </div>
                                        <ul className="list-disc pl-6">
                                            <li className="text-sm">Nhà sản xuất: {printer.manufacturer}</li>
                                            <li className="text-sm">Mẫu máy in: {printer.model}</li>
                                            <li className="text-sm">Tên trường: {printer.campusName}</li>
                                            <li className="text-sm">Tòa: {printer.buildingName}</li>
                                            <li className="text-sm">Số phòng: {printer.roomNumber}</li>
                                        </ul>
                                    </div>
                                )
                            }
                        </div>
                    </div>
                    <button
                        type='button'
                        className="text-white p-4 rounded-lg bg-[#EC221F] w-[100px] absolute bottom-5 left-10 hover:bg-blue-600"
                        onClick={() => setOrder(null)}
                    >
                        Quay lại
                    </button>
                </>
            }
        </div>
    )
}

export default ConfigDoc
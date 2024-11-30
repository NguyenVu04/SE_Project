import React from 'react'
import Link from 'next/link';

const ConfigDoc = () => {
  return (
    <div
      style={{ position: 'absolute', top: 70, left: 150, right: 150, bottom: 30 }}
      className="bg-white flex flex-col items-center py-5 px-20" // Sử dụng justify-center để căn giữa
    >
        <h2 className="text-3xl mt-2">CHỌN THÔNG SỐ IN</h2>
        <hr className="border-b border-black w-full mb-20 mt-2" />
        <div className='flex flex-col justify-between items-center h-full w-full'>
            <div className="flex flex-row justify-start space-x-32 w-full">
                {/* Container cho hình ảnh và thông tin tài liệu */}
                <div className="flex flex-col items-center">
                    <div className="bg-gray-300 w-48 h-64 mb-4"></div> {/* Placeholder cho hình ảnh */}
                    <span className="text-xl mb-4">myfile.pdf</span>
                </div>
                <div className="flex flex-col space-y-8">
                    {/* Options */}
                    <label className="flex items-center space-x-4">
                        <input type="radio" name="pages" value="all" defaultChecked className="w-6 h-6"/>
                        <span className="text-2xl">Tất cả trang</span>
                    </label>
                    <label className="flex items-center space-x-4">
                        <input type="radio" name="pages" value="selected" className="w-6 h-6" />
                        <span className="text-2xl">Trang được chọn:</span>
                        <input type="text" placeholder="e.g. 1-5, 8, 11-13" className="p-2 border border-gray-300 rounded" />
                    </label>

                    <label className="flex items-center space-x-4">
                        <input type="checkbox" className="w-6 h-6"/>
                        <span className="text-2xl">In hai mặt</span>
                    </label>

                    <label className="flex items-center space-x-4">
                        <span className="text-2xl">Cỡ giấy:</span>
                        
                        <select className="p-2 w-28 border border-gray-300 rounded">
                            <option value="A4">A4</option>
                            <option value="A5">A5</option>
                            <option value="Letter">Letter</option>
                            <option value="Legal">Legal</option>
                        </select>
                    </label>

                    <label className="flex items-center space-x-4">
                        <span className="text-2xl">Số bản in:</span>
                        <input
                            type="number"
                            defaultValue={1}
                            min="1"
                            className="p-2 w-28 border border-gray-300 rounded"
                        />
                    </label>

                </div>
            </div>
            {/* Buttons */}
            <div className="flex space-x-4 w-full justify-between">
                <Link href="/student">
                    <button
                        style = {{background: '#EC221F', width: '100px'}}
                        className="text-white p-4 rounded-lg"
                    >
                        Quay lại
                    </button>
                </Link>

                <Link href="/student/printer">
                    <button
                        style = {{background: '#08FF3D', width: '100px'}}
                        className="p-4 rounded-lg border-black border-2"
                    >
                        Tiếp tục
                    </button>
                </Link>
            </div>
        </div>
    </div>
  )
}

export default ConfigDoc
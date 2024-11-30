import ChooseDocument from '@/components/ChooseDocument'
import React from 'react'

const page = () => {
  return (
    <div
      className="bg-gray-100 w-screen min-h-screen flex flex-col bg-gradient-to-b from-[#0381FF] to-[#02067A]">
        <ChooseDocument />
    </div>
  )
}

export default page
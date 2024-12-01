import ConfigDoc from '@/components/ConfigDoc'
import React from 'react'
export type OrderInfo = {
  filename: string
  paperSize: string
  pageNumbers: number[],
  singleSided: boolean
  numberOfCopies: number
}

const studentId = "674a95a79654906c311d05da";
const Page = () => {
  return (
    <div
      className="bg-gray-100 w-screen min-h-screen flex flex-col bg-gradient-to-b from-[#0381FF] to-[#02067A]">
        <ConfigDoc studentId={studentId}/>
    </div>
  )
}

export default Page
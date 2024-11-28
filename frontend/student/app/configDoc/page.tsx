import ConfigDoc from '@/components/ConfigDoc'
import React from 'react'

const page = () => {
  return (
    <div 
      style={{
        background: 'linear-gradient(to bottom, #0381FF, #02067A)'
      }}
      className="bg-gray-100 w-screen min-h-screen flex flex-col">
        <ConfigDoc />
    </div>
  )
}

export default page
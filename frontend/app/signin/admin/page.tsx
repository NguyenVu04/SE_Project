import Link from 'next/link'
import { Form as LoginForm } from 'frontend/app/signin/form'

export default function LoginPageAdmin() {
  return (
    <div className="h-screen w-screen flex justify-center items-center bg-slate-100" style={{
        background: 'linear-gradient(to bottom, #0381FF, #02067A)'
      }}>
      <div className="sm:shadow-xl px-8 pb-8 pt-12 sm:bg-white rounded-xl space-y-12">
        <h1 className="font-semibold text-2xl">Admin Login</h1>
        <LoginForm />
        <p className="text-center">
          Need to create an account?{' '}
          <Link className="text-indigo-500 hover:underline" href="/register">
            Create Account
          </Link>{' '}
        </p>
      </div>
    </div>
  )
}
import { AuthOptions } from "next-auth"
import GoogleProvider from "next-auth/providers/google"

export const options: AuthOptions = {
    providers: [
        GoogleProvider({
            clientId: process.env.GOOGLE_CLIENT_ID as string,
            clientSecret: process.env.GOOGLE_CLIENT_SECRET as string,
        })
    ],
    callbacks: {
        async signIn({ user }) {
            if (user.email?.endsWith("@hcmut.edu.vn") || user.email?.endsWith("@gmail.com")) 
                return true;
            return false;
        },
    },
    secret: process.env.NEXTAUTH_SECRET,
    pages: {
        signIn: "/signin",
    }
}
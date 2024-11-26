import GoogleProvider from "next-auth/providers/google"

export const options = {
    providers: [
        GoogleProvider({
            clientId: process.env.GOOGLE_CLIENT_ID as string,
            clientSecret: process.env.GOOGLE_CLIENT_SECRET as string,
        })
    ],
    secret: process.env.NEXTAUTH_SECRET,
    // pages: {
    //     signIn: "/signin",
    //     signOut: "/signout"
    // }
}
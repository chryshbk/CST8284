/* echoserver.c */

/*-----------------------------------------------------------------------
 *	Original code in "c" format from:
 *		Computer Networks and Internets, 3rd Edition.
 *		Douglas E. Comer. (2001)	Prentice Hall.
 *	Modified:	R. Dyer	2002.
 *-----------------------------------------------------------------------
 */

#include <iostream>
using std::cout;
using std::cin;

#include <iomanip>
using std::setw;
using std::endl;

#include  "../api/cnaiapi.h"

/*-----------------------------------------------------------------------
 *
 * Program: echoserver
 * Purpose: wait for a connection from an echoclient and echo data
 * Usage:   echoserver <appnum>
 *
 *-----------------------------------------------------------------------
 */
int	main(int argc, char *argv[])	{
	/** Buffer size.																*/
	const int BUFFER_SIZE = 256;
	/** Socket descriptor for service.										*/
	connection	conn;
	/** Length of buffer.														*/
	int len;
	/** Input buffer.																*/
	char buff[BUFFER_SIZE];

	if (argc != 2) {
		(void) fprintf(stderr, "usage: %s <appnum>\n", argv[0]);
		exit(1);
	}

	/* Verify that the user passed the command line arguments.		*/
	if (argc != 2)	{
		cout << "\n\tUsage: " << argv[0]
			 << " <appnum or port#>"
			 << "\n\tExample:"
			 << "\n\t" << argv[0]
			 << " 7"
			 << "\n\n\t(Start the server on port number 7"
			 << "\n\n" << endl;
		exit(1);
	}

	/*	Wait for a connection from an echo client,
	 *	if no client it waits for a new one.
	 */
        while(true){
            conn = await_contact((appnum) atoi(argv[1]));
            if (conn < 0)	exit(1);

            /* iterate, echoing all data received until end of file */
            while ((len = recv(conn, buff, BUFFER_SIZE, 0)) > 0)
                    (void) send(conn, buff, len, 0);
            send_eof(conn);
        }
	return 0;
}

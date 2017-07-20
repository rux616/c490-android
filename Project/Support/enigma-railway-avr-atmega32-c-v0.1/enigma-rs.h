#define FCLK 16000000

/* ========================================================================== */
/* USART Related Constants                                                    */
/* ========================================================================== */
#define USART_DOUBLE_SPEED
#define BAUD_RATE 115200

#ifdef USART_DOUBLE_SPEED
    #define UBRR_VALUE ((long)(FCLK/(8*BAUD_RATE ))-1)
#else
    #define UBRR_VALUE ((long)(FCLK/(18*BAUD_RATE))-1)
#endif



/* ========================================================================== */
/* Enigma Railways's Rotors and Permutations                                  */
/* ========================================================================== */
/*
   Rotor I
   -------------------------------------------------------------------------------
   |A |B |C |D |E |F |G |H |I |J |K |L |M |N |O |P |Q |R |S |T |U |V |W |X |Y |Z |
   |00|01|02|03|04|05|06|07|08|09|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24|25|
   -------------------------------------------------------------------------------
   |J |G |D |Q |O |X |U |S |C |A |M |I |F |R |V |T |P |N |E |W |K |B |L |Z |Y |H |
   |09|06|03|16|14|23|20|18|02|00|12|08|05|17|21|19|15|13|04|22|10|01|11|25|24|07|
   -------------------------------------------------------------------------------

   Inverse Rotor I
   -------------------------------------------------------------------------------
   |A |B |C |D |E |F |G |H |I |J |K |L |M |N |O |P |Q |R |S |T |U |V |W |X |Y |Z |
   |00|01|02|03|04|05|06|07|08|09|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24|25|
   -------------------------------------------------------------------------------
   |J |V |I |C |S |M |B |Z |L |A |U |W |K |R |E |Q |D |N |H |P |G |O |T |F |Y |X |
   |09|21|08|02|18|12|01|25|11|00|20|22|10|17|04|16|03|13|07|15|06|14|19|05|24|23|
   -------------------------------------------------------------------------------

   Rotor II
   -------------------------------------------------------------------------------
   |A |B |C |D |E |F |G |H |I |J |K |L |M |N |O |P |Q |R |S |T |U |V |W |X |Y |Z |
   |00|01|02|03|04|05|06|07|08|09|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24|25|
   -------------------------------------------------------------------------------
   |N |T |Z |P |S |F |B |O |K |M |W |R |C |J |D |I |V |L |A |E |Y |U |X |H |G |Q |
   |13|19|25|15|18|05|01|14|10|12|22|17|02|09|03|08|21|11|00|04|24|20|23|07|06|16|
   -------------------------------------------------------------------------------

   Inverse Rotor II
   -------------------------------------------------------------------------------
   |A |B |C |D |E |F |G |H |I |J |K |L |M |N |O |P |Q |R |S |T |U |V |W |X |Y |Z |
   |00|01|02|03|04|05|06|07|08|09|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24|25|
   -------------------------------------------------------------------------------
   |S |G |M |O |T |F |Y |X |P |N |I |R |J |A |H |D |Z |L |E |B |V |Q |K |W |U |C |
   |18|06|12|14|19|05|24|23|15|13|08|17|09|00|07|03|25|11|04|01|21|16|10|22|20|02|
   -------------------------------------------------------------------------------

   Rotor III
   -------------------------------------------------------------------------------
   |A |B |C |D |E |F |G |H |I |J |K |L |M |N |O |P |Q |R |S |T |U |V |W |X |Y |Z |
   |00|01|02|03|04|05|06|07|08|09|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24|25|
   -------------------------------------------------------------------------------
   |J |V |I |U |B |H |T |C |D |Y |A |K |E |Q |Z |P |O |S |G |X |N |R |M |W |F |L |
   |09|21|08|20|01|07|19|02|03|24|00|10|04|16|25|15|14|18|06|23|13|17|12|22|05|11|
   -------------------------------------------------------------------------------

   Inverse Rotor III
   -------------------------------------------------------------------------------
   |A |B |C |D |E |F |G |H |I |J |K |L |M |N |O |P |Q |R |S |T |U |V |W |X |Y |Z |
   |00|01|02|03|04|05|06|07|08|09|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24|25|
   -------------------------------------------------------------------------------
   |K |E |H |I |M |Y |S |F |C |A |L |Z |W |U |Q |P |N |V |R |G |D |B |X |T |J |O |
   |10|04|07|08|12|24|18|05|02|00|11|25|22|20|16|15|13|21|17|06|03|01|23|19|09|14|
   -------------------------------------------------------------------------------

   UKW Reflector
   -------------------------------------------------------------------------------
   |A |B |C |D |E |F |G |H |I |J |K |L |M |N |O |P |Q |R |S |T |U |V |W |X |Y |Z |
   |00|01|02|03|04|05|06|07|08|09|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24|25|
   -------------------------------------------------------------------------------
   |Q |Y |H |O |G |N |E |C |V |P |U |Z |T |F |D |J |A |X |W |M |K |I |S |R |B |L |
   |16|24|07|14|06|13|04|02|21|15|20|25|19|05|03|09|00|23|22|12|10|08|18|17|01|11|
   -------------------------------------------------------------------------------

   Standard Germany Keyboard Permutation
   -------------------------------------------------------------------------------
   |A |B |C |D |E |F |G |H |I |J |K |L |M |N |O |P |Q |R |S |T |U |V |W |X |Y |Z |
   |00|01|02|03|04|05|06|07|08|09|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24|25|
   -------------------------------------------------------------------------------
   |J |W |U |L |C |M |N |O |H |P |Q |Z |Y |X |I |R |A |D |K |E |G |V |B |T |S |F |
   |09|22|20|11|02|12|13|14|07|15|16|25|24|23|08|17|00|03|10|04|06|21|01|19|18|05|
   -------------------------------------------------------------------------------

   Inverse Standard Germany Keyboard Permutation
   -------------------------------------------------------------------------------
   |A |B |C |D |E |F |G |H |I |J |K |L |M |N |O |P |Q |R |S |T |U |V |W |X |Y |Z |
   |00|01|02|03|04|05|06|07|08|09|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24|25|
   -------------------------------------------------------------------------------
   |Q |W |E |R |T |Z |U |I |O |A |S |D |F |G |H |J |K |P |Y |X |C |V |B |N |M |L |
   |16|22|04|17|19|25|20|08|14|00|18|03|05|06|07|09|10|15|24|23|02|21|01|13|12|11|
   -------------------------------------------------------------------------------
*/
flash unsigned char rot[3][26] = { {0x09,0x06,0x03,0x10,0x0e,0x17,0x14,0x12,0x02,0x00,0x0c,0x08,0x05,
                                    0x11,0x15,0x13,0x0f,0x0d,0x04,0x16,0x0a,0x01,0x0b,0x19,0x18,0x07},
                                   {0x0d,0x13,0x19,0x0f,0x12,0x05,0x01,0x0e,0x0a,0x0c,0x16,0x11,0x02,
                                    0x09,0x03,0x08,0x15,0x0b,0x00,0x04,0x18,0x14,0x17,0x07,0x06,0x10},
                                   {0x09,0x15,0x08,0x14,0x01,0x07,0x13,0x02,0x03,0x18,0x00,0x0a,0x04,
                                    0x10,0x19,0x0f,0x0e,0x12,0x06,0x17,0x0d,0x11,0x0c,0x16,0x05,0x0b}};

flash unsigned char irot[3][26] = {{0x09,0x15,0x08,0x02,0x12,0x0c,0x01,0x19,0x0b,0x00,0x14,0x16,0x0a,
	                                0x11,0x04,0x10,0x03,0x0d,0x07,0x0f,0x06,0x0e,0x13,0x05,0x18,0x17},
                                   {0x12,0x06,0x0c,0x0e,0x13,0x05,0x18,0x17,0x0f,0x0d,0x08,0x11,0x09,
                                    0x00,0x07,0x03,0x19,0x0b,0x04,0x01,0x15,0x10,0x0a,0x16,0x14,0x02},
                                   {0x0a,0x04,0x07,0x08,0x0c,0x18,0x12,0x05,0x02,0x00,0x0b,0x19,0x16,
                                    0x14,0x10,0x0f,0x0d,0x15,0x11,0x06,0x03,0x01,0x17,0x13,0x09,0x0e}};

flash unsigned char ukw[26]    = {0x10,0x18,0x07,0x0e,0x06,0x0d,0x04,0x02,0x15,0x0f,0x14,0x19,0x13,
                                  0x05,0x03,0x09,0x00,0x17,0x16,0x0c,0x0a,0x08,0x12,0x11,0x01,0x0b};

flash unsigned char kyb[2][26] = {{0x09,0x16,0x14,0x0b,0x02,0x0c,0x0d,0x0e,0x07,0x0f,0x10,0x19,0x18,
                                   0x17,0x08,0x11,0x00,0x03,0x0a,0x04,0x06,0x15,0x01,0x13,0x12,0x05},
                                  {0x10,0x16,0x04,0x11,0x13,0x19,0x14,0x08,0x0e,0x00,0x12,0x03,0x05,
                                   0x06,0x07,0x09,0x0a,0x0f,0x18,0x17,0x02,0x15,0x01,0x0d,0x0c,0x0b}};

flash unsigned char notch[3]   = {'N'-'A','E'-'A','Y'-'A'};




/* ========================================================================== */
/* Function Prototypes                                                        */
/* ========================================================================== */
void usart_init(void);
signed char encode(signed char *in, signed char *out);
void reset(void);
signed char shift(signed char a, signed char b, signed char c);
signed char ishift(signed char a, signed char b, signed char c);
void rotate_fwd(signed char *rotor_pos, unsigned char mod);
void rotate_bwd(signed char *rotor_pos, unsigned char mod);
void show_config(void);
void store_config(void);
void load_config(void);
void default_config(void);
void print_help(unsigned char menu);



/* ========================================================================== */
/* Misc Constants                                                             */
/* ========================================================================== */
#define TRUE  1
#define FALSE 0
#define MENU_ABOUT   0
#define MENU_LICENSE 1
#define MENU_USAGE   2




/* ========================================================================== */
/* ERROR Constants                                                            */
/* ========================================================================== */
#define ERR_NO      0
#define ERR_INVALID 1   /* Invalid Character */
#define MAX_TYPE    3
#define MAX_POS     26
#define MAX_SET     26




/* ========================================================================== */
/* Rotor Types                                                                */
/* ========================================================================== */
#define ROTOR_1 0
#define ROTOR_2 1
#define ROTOR_3 2




/* ========================================================================== */
/* Bit Definition                                                             */
/* ========================================================================== */
/* Register GICR */
#define INT1    7
#define INT0    6
#define INT2    5
#define IVSEL   1
#define IVCE    0

/* Register GIFR */
#define INTF1   7
#define INTF0   6
#define INTF2   5

/* Register TIMSK */
#define OCIE2   7
#define TOIE2   6
#define TICIE1  5
#define OCIE1A  4
#define OCIE1B  3
#define TOIE1   2
#define OCIE0   1
#define TOIE0   0

/* Register TIFR */
#define OCF2    7
#define TOV2    6
#define ICF1    5
#define OCF1A   4
#define OCF1B   3
#define TOV1    2
#define OCF0    1
#define TOV0    0

/* Register SPMCR */
#define SPMIE   7
#define RWWSB   6
#define RWWSRE  4
#define BLBSET  3
#define PGWRT   2
#define PGERS   1
#define SPMEN   0

/* Register TWCR */
#define TWINT   7
#define TWEA    6
#define TWSTA   5
#define TWSTO   4
#define TWWC    3
#define TWEN    2
#define TWIE    0

/* Register TWAR */
#define TWA6    7
#define TWA5    6
#define TWA4    5
#define TWA3    4
#define TWA2    3
#define TWA1    2
#define TWA0    1
#define TWGCE   0

/* Register TWSR */
#define TWS7    7
#define TWS6    6
#define TWS5    5
#define TWS4    4
#define TWS3    3
#define TWPS1   1
#define TWPS0   0

/* Register MCUCR */
#define SE      7
#define SM2     6
#define SM1     5
#define SM0     4
#define ISC11   3
#define ISC10   2
#define ISC01   1
#define ISC00   0

/* Register MCUCSR */
#define JTD     7
#define ISC2    6
#define JTRF    4
#define WDRF    3
#define BORF    2
#define EXTRF   1
#define PORF    0

/* Register SFIOR */
#define ADTS2   7
#define ADTS1   6
#define ADTS0   5
#define ACME    3
#define PUD     2
#define PSR2    1
#define PSR10   0

/* Register TCCR0 */
#define FOC0    7
#define WGM00   6
#define COM01   5
#define COM00   4
#define WGM01   3
#define CS02    2
#define CS01    1
#define CS00    0

/* Register TCCR2 */
#define FOC2    7
#define WGM20   6
#define COM21   5
#define COM20   4
#define WGM21   3
#define CS22    2
#define CS21    1
#define CS20    0

/* Register ASSR */
#define AS2     3
#define TCN2UB  2
#define OCR2UB  1
#define TCR2UB  0

/* Register TCCR1A */
#define COM1A1  7
#define COM1A0  6
#define COM1B1  5
#define COM1B0  4
#define FOC1A   3
#define FOC1B   2
#define WGM11   1
#define WGM10   0

/* Register TCCR1B */
#define ICNC1   7
#define ICES1   6
#define WGM13   4
#define WGM12   3
#define CS12    2
#define CS11    1
#define CS10    0

/* Register WDTCR */
#define WDTOE   4
#define WDE     3
#define WDP2    2
#define WDP1    1
#define WDP0    0

/* Register PORTA */
#define PA7     7
#define PA6     6
#define PA5     5
#define PA4     4
#define PA3     3
#define PA2     2
#define PA1     1
#define PA0     0

/* Register DDRA */
#define DDA7    7
#define DDA6    6
#define DDA5    5
#define DDA4    4
#define DDA3    3
#define DDA2    2
#define DDA1    1
#define DDA0    0

/* Register PINA */
#define PINA7   7
#define PINA6   6
#define PINA5   5
#define PINA4   4
#define PINA3   3
#define PINA2   2
#define PINA1   1
#define PINA0   0

/* Register PORTB */
#define PB7     7
#define PB6     6
#define PB5     5
#define PB4     4
#define PB3     3
#define PB2     2
#define PB1     1
#define PB0     0

/* Register DDRB */
#define DDB7    7
#define DDB6    6
#define DDB5    5
#define DDB4    4
#define DDB3    3
#define DDB2    2
#define DDB1    1
#define DDB0    0

/* Register PINB */
#define PINB7   7
#define PINB6   6
#define PINB5   5
#define PINB4   4
#define PINB3   3
#define PINB2   2
#define PINB1   1
#define PINB0   0

/* Register PORTC */
#define PC7     7
#define PC6     6
#define PC5     5
#define PC4     4
#define PC3     3
#define PC2     2
#define PC1     1
#define PC0     0

/* Register DDRC */
#define DDC7    7
#define DDC6    6
#define DDC5    5
#define DDC4    4
#define DDC3    3
#define DDC2    2
#define DDC1    1
#define DDC0    0

/* Register PINC */
#define PINC7   7
#define PINC6   6
#define PINC5   5
#define PINC4   4
#define PINC3   3
#define PINC2   2
#define PINC1   1
#define PINC0   0

/* Register PORTD */
#define PD7     7
#define PD6     6
#define PD5     5
#define PD4     4
#define PD3     3
#define PD2     2
#define PD1     1
#define PD0     0

/* Register DDRD */
#define DDD7    7
#define DDD6    6
#define DDD5    5
#define DDD4    4
#define DDD3    3
#define DDD2    2
#define DDD1    1
#define DDD0    0

/* Register PIND */
#define PIND7   7
#define PIND6   6
#define PIND5   5
#define PIND4   4
#define PIND3   3
#define PIND2   2
#define PIND1   1
#define PIND0   0

/* Register SPSR */
#define SPIF    7
#define WCOL    6
#define SPI2X   0

/* Register SPCR */
#define SPIE    7
#define SPE     6
#define DORD    5
#define MSTR    4
#define CPOL    3
#define CPHA    2
#define SPR1    1
#define SPR0    0

/* Register UCSRA */
#define RXC     7
#define TXC     6
#define UDRE    5
#define FE      4
#define DOR     3
#define PE      2
#define U2X     1
#define MPCM    0

/* Register UCSRB */
#define RXCIE   7
#define TXCIE   6
#define UDRIE   5
#define RXEN    4
#define TXEN    3
#define UCSZ2   2
#define RXB8    1
#define TXB8    0

/* Register UCSRC */
#define URSEL   7
#define UMSEL   6
#define UPM1    5
#define UPM0    4
#define USBS    3
#define UCSZ1   2
#define UCSZ0   1
#define UCPOL   0

/* Register ACSR */
#define ACD     7
#define ACBG    6
#define ACO     5
#define ACI     4
#define ACIE    3
#define ACIC    2
#define ACIS1   1
#define ACIS0   0

/* Register ADCSRA */
#define ADEN    7
#define ADSC    6
#define ADATE   5
#define ADIF    4
#define ADIE    3
#define ADPS2   2
#define ADPS1   1
#define ADPS0   0

/* Register ADMUX */
#define REFS1   7
#define REFS0   6
#define ADLAR   5
#define MUX4    4
#define MUX3    3
#define MUX2    2
#define MUX1    1
#define MUX0    0

/* Register EECR */
#define EERIE   3
#define EEMWE   2
#define EEWE    1
#define EERE    0

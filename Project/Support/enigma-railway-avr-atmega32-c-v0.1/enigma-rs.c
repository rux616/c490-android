/*
;===============================================================================
; The implementation of Enigma Railway using ATMEGA 32                         ;
; Copyright (C) 2008 Edi Permadi                                               ;
;===============================================================================
;                                                                              ;
; Author          : Edi Permadi                                                ;
; Date Coded      : June 12, 2009                                              ;
; Version         : 1.0                                                        ;
; Last Modified   : June 12, 2009                                              ;
; downloaded from : http://edipermadi.wordpress.com                            ;
;                                                                              ;
;===============================================================================
;                                                                              ;
; This program is free software: you can redistribute it and/or modify         ;
; it under the terms of the GNU General Public License as published by         ;
; the Free Software Foundation, either version 3 of the License, or            ;
; (at your option) any later version.                                          ;
;                                                                              ;
; This program is distributed in the hope that it will be useful,              ;
; but WITHOUT ANY WARRANTY; without even the implied warranty of               ;
; MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the                ;
; GNU General Public License for more details.                                 ;
;                                                                              ;
; You should have received a copy of the GNU General Public License            ;
; along with this program.  If not, see <http://www.gnu.org/licenses/>.        ;
;                                                                              ;
;===============================================================================

    This C source code implements the Enigma Railway using ATMega32 at 16MHz
system frequency. The C code is written using Codevision 1.25.3. You may also
port this code into AVR GCC and so on.

    The code is mainly divided into initialization handler, configuration
handler, event handler, communication handler, encoding routine, and lookup
tables. The lookup tables are limited to Rotor I, II and III, UKW Reflector and
the Germany Standard Keyboard permutation.

    This code is released as an open source without any warranty implied. Thus
any kind of damages caused by this implementation are subject to the user. The
author has tested the implementation in a certain circumtances with satisfactory
result. Please take your own risk before using this and please mail or leave
comment on my blog as your response.

    The simulation is done by sending 600 "A" characters with configuration
listed below:

Rotor Type     : UKW/I/II/III
Rotor Setting  : A/A/A/A
Rotor Position : A/A/A/A

*/




/*******************************************************************************
 Import Necessary Headers
 ******************************************************************************/
#include <mega32.h>
#include <stdio.h>
#include <ctype.h>
#include "enigma-rs.h"




/*******************************************************************************
 Inner Setting Buffer
 ******************************************************************************/
signed char rot_l_type;   /* Type of rotor located on left slot */
signed char rot_m_type;   /* Type of rotor located on middle slot */
signed char rot_r_type;   /* Type of rotor located on right slot */
signed char rot_l_set;    /* Setting of rotor located on left slot */
signed char rot_m_set;    /* Setting of rotor located on middle slot */
signed char rot_r_set;    /* Setting of rotor located on right slot */
signed char rot_x_set;    /* Setting of rotor located on reflextor slot */
signed char rot_l_pos;    /* Position of rotor located on left slot */
signed char rot_m_pos;    /* Position of rotor located on middle slot */
signed char rot_r_pos;    /* Position of rotor located on right slot */
signed char rot_x_pos;    /* Position of Rotor located on reflector slot */




/*******************************************************************************
 Configuration Backup on EEPROM
 ******************************************************************************/
eeprom signed char backup_rot_l_type;   /* Type of rotor located on left slot */
eeprom signed char backup_rot_m_type;   /* Type of rotor located on middle slot */
eeprom signed char backup_rot_r_type;   /* Type of rotor located on right slot */
eeprom signed char backup_rot_l_set;    /* Setting of rotor located on left slot */
eeprom signed char backup_rot_m_set;    /* Setting of rotor located on middle slot */
eeprom signed char backup_rot_r_set;    /* Setting of rotor located on right slot */
eeprom signed char backup_rot_x_set;    /* Setting of rotor located on reflextor slot */
eeprom signed char backup_rot_l_pos;    /* Position of rotor located on left slot */
eeprom signed char backup_rot_m_pos;    /* Position of rotor located on middle slot */
eeprom signed char backup_rot_r_pos;    /* Position of rotor located on right slot */
eeprom signed char backup_rot_x_pos;    /* Position of Rotor located on reflector slot */
eeprom unsigned char first_on = TRUE;
eeprom unsigned char autoshow_config = FALSE;




/*******************************************************************************
 Main Entrance
 ******************************************************************************/
void main(void){
    signed char tmp_in;     /* incoming character buffer */
    signed char tmp_out;    /* outgoing character buffer */
    signed char ret;        /* successful encoding indicator */
    signed char i;          /* printing index */

    usart_init();   /* initialize USART */
    reset();        /* reset enigma */
    i = 0;          /* reset printing index */

    while(1){
        tmp_in = getchar();
        if(isalpha(tmp_in)){
            /* proceed if given input is an alphabet */
            ret = encode(&tmp_in, &tmp_out);

            /* proceed if no error occured */
            if(!ret){
                /* print encoded character */
                putchar(tmp_out);

                /* update printing index */
                i++;

                /* apply grouping */
                if(i == 5){
                    putchar(' ');
                    i = 0;
                }
            }
        }else{
            /* reset printing index */
            i = 0;

            /* Process setting commands */
            switch(tmp_in){
                case '`' : /* Toggle on/off autoshow_config */
                           if(autoshow_config){
                               autoshow_config = FALSE;
                               putsf("\n\nAuto Show Configuration : [OFF]\n");
                           }else{
                               autoshow_config = TRUE;
                               putsf("\n\nAuto Show Configuration : [ON]\n");
                           }
                           break;
                case '!' : /* forward reflector setting */
                           rotate_fwd(&rot_x_set, MAX_SET);
                           break;
                case '@' : /* forward left rotor setting */
                           rotate_fwd(&rot_l_set, MAX_SET);
                           break;
                case '#' : /* forward middle rotor setting */
                           rotate_fwd(&rot_m_set, MAX_SET);
                           break;
                case '$' : /* forward right rotor setting */
                           rotate_fwd(&rot_r_set, MAX_SET);
                           break;
                case '1' : /* backward reflector setting */
                           rotate_bwd(&rot_x_set, MAX_SET);
                           break;
                case '2' : /* forward left rotor setting */
                           rotate_bwd(&rot_l_set, MAX_SET);
                           break;
                case '3' : /* forward middle rotor setting */
                           rotate_bwd(&rot_m_set, MAX_SET);
                           break;
                case '4' : /* forward right rotor setting */
                           rotate_bwd(&rot_r_set, MAX_SET);
                           break;
                case '%' : /* forward reflector position */
                           rotate_fwd(&rot_x_pos, MAX_POS);
                           break;
                case '^' : /* forward left rotor position */
                           rotate_fwd(&rot_l_pos, MAX_POS);
                           break;
                case '&' : /* forward middle rotor position */
                           rotate_fwd(&rot_m_pos, MAX_POS);
                           break;
                case '*' : /* forward right rotor position */
                           rotate_fwd(&rot_r_pos, MAX_POS);
                           break;
                case '5' : /* backward reflector position */
                           rotate_bwd(&rot_x_pos, MAX_POS);
                           break;
                case '6' : /* backward left rotor position */
                           rotate_bwd(&rot_l_pos, MAX_POS);
                           break;
                case '7' : /* backward middle rotor position */
                           rotate_bwd(&rot_m_pos, MAX_POS);
                           break;
                case '8' : /* backward right rotor position */
                           rotate_bwd(&rot_r_pos, MAX_POS);
                           break;
                case '(' : /* forward rotor type on left slot */
                           rotate_fwd(&rot_l_type, MAX_TYPE);
                           break;
                case ')' : /* forward rotor type on middle slot */
                           rotate_fwd(&rot_m_type, MAX_TYPE);
                           break;
                case '_' : /* forward rotor type on right slot */
                           rotate_fwd(&rot_r_type, MAX_TYPE);
                           break;
                case '9' : /* backward rotor type on left slot */
                           rotate_bwd(&rot_l_type, MAX_TYPE);
                           break;
                case '0' : /* backward rotor type on middle slot */
                           rotate_bwd(&rot_m_type, MAX_TYPE);
                           break;
                case '-' : /* backward rotor type on right slot */
                           rotate_bwd(&rot_r_type, MAX_TYPE);
                           break;
                case '=' : /* Load default configuration */
                           putsf("\n\nLoad Default Configuration (y/n)?");
                           tmp_in = toupper(getchar());
                           if(tmp_in == 'Y'){
                               default_config();
                               store_config();      /* Indicate that a new value is saved */
                               putsf("Set to Default!\n");
                           }else{
                               putsf("Default Configuration Canceled!\n");
                           }

                           break;
                case '+' : /* store confguration */
                           putsf("\n\nSave Configuration (y/n)?");
                           tmp_in = toupper(getchar());
                           if(tmp_in == 'Y'){
                               /* store current configuration */
                               store_config();
                               first_on = FALSE;       /* Indicate that a new value is saved */
                               putsf("Saved!\n");
                           }else{
                               putsf("Not Saved!\n");
                           }
                           break;
                case '?' : /* manual pages */
                           putsf("\n\nPlease Select Help Page [1-3]");
                           putsf("[1] About");
                           putsf("[2] License");
                           putsf("[3] Usage\n");
                           tmp_in = getchar();
                           switch(tmp_in){
                               case '1' : print_help(MENU_ABOUT);
                                          break;
                               case '2' : print_help(MENU_LICENSE);
                                          break;
                               case '3' : print_help(MENU_USAGE);
                                          break;
                           }
                           break;

            }

            /* proceed if autoshow_config lag is active*/
            if(autoshow_config){
                show_config();
            }
        }
    }
}




/*******************************************************************************
 USART Initialization

    This routine will initialize the USART module to work at 115200 bps, 8 bit
 transmission, single stop bit and no parity bit. In addition, the USART is set
 to run a free mode without flow control.
 ******************************************************************************/
void usart_init(void){
    UBRRH = (UBRR_VALUE >> 8) & 0xff;
    UBRRL = (UBRR_VALUE >> 0) & 0xff;
    UCSRA = (1 << U2X);
    UCSRB = (1 << RXEN  ) |         /* Enable Receive */
            (1 << TXEN  );          /* Enable Transmit */
    UCSRC = (1 << URSEL) |          /* Write to UCSRC */
            (3 << UCSZ0);           /* Use 8 Bit Transmission */
}




/*******************************************************************************
 Enigma Encoding

    This routine will convert incoming alphabet characters. The conversion is
 done by using finite arithetic and array lookup. The array lookup emulates the
 working of enigma rotor. In addition, the input characters is first permutated
 using the standard germany keyboard.
 ******************************************************************************/
signed char encode(signed char *in, signed char *out){
    signed char tmp;

    tmp = toupper(*in);             /* Convert to uppercase */

    if(tmp < 'A' || tmp > 'Z'){     /* On invalid character, return */
        return ERR_INVALID;
    }

    tmp = kyb[0][(tmp - 'A') %26];  /* Normalize */

    /* detect notch advancing */
    if(rot_m_pos == notch[rot_m_type]){
        rotate_fwd(&rot_l_pos, MAX_POS);
        rotate_fwd(&rot_m_pos, MAX_POS);
        goto proceed;
    }

    /* detect notch advancing */
    if(rot_r_pos == notch[rot_r_type]){
        rotate_fwd(&rot_m_pos, MAX_POS);
    }

proceed:
    rotate_fwd(&rot_r_pos, MAX_POS);    /* update right rotor position */

    /* send through right rotor */
    tmp = shift(tmp, rot_r_pos, rot_r_set);
    tmp = rot[rot_r_type][tmp];
    tmp = ishift(tmp, rot_r_pos, rot_r_set);

    /* send through middle rotor */
    tmp = shift(tmp, rot_m_pos, rot_m_set);
    tmp = rot[rot_m_type][tmp];
    tmp = ishift(tmp, rot_m_pos, rot_m_set);

    /* send through left rotor */
    tmp = shift(tmp, rot_l_pos, rot_l_set);
    tmp = rot[rot_l_type][tmp];
    tmp = ishift(tmp, rot_l_pos, rot_l_set);

    /* send through reflector */
    tmp = shift(tmp,rot_x_pos,rot_x_set);
    tmp = ukw[tmp];
    tmp = ishift(tmp,rot_x_pos,rot_x_set);

    /* send back through left rotor */
    tmp = shift(tmp, rot_l_pos, rot_l_set);
    tmp = irot[rot_l_type][tmp];
    tmp = ishift(tmp, rot_l_pos, rot_l_set);

    /* send back through middle rotor */
    tmp = shift(tmp, rot_m_pos, rot_m_set);
    tmp = irot[rot_m_type][tmp];
    tmp = ishift(tmp, rot_m_pos, rot_m_set);

    /* send back through right rotor */
    tmp = shift(tmp, rot_r_pos, rot_r_set);
    tmp = irot[rot_r_type][tmp];
    tmp = ishift(tmp, rot_r_pos, rot_r_set);

    *out = kyb[1][tmp] + 'A';       /* descramble and convert to character */
    return ERR_NO;
}

/*******************************************************************************
 Reset Enigma

    The reseting enigma function will initialize the railway enigma whether
 using default value of stored value. The default value will be loaded when no
 alternative configuration is stored on EEPROM. The configuration buffer on
 EEPROM wil be epty until the first attempt to store confiuration. Once an
 alternative configuration is stored, the enigma will alwas refer to the stored
 setting on its bootup process.
 ******************************************************************************/
void reset(void){
    if(first_on){
        default_config();
    }else{
        load_config();
    }
}




/*******************************************************************************
 Enigma Shifting

    This routine emulates the rottating characteristic of enigma rotors. This
 routine specifically emulate the right to left pre substitution.
 ******************************************************************************/
signed char shift(signed char a, signed char b, signed char c){
    signed char tmp;

    tmp  = (a + b) % MAX_POS;
    tmp -= c;
    if(tmp < 0){
        tmp += MAX_POS;
    }
    return tmp;
}




/*******************************************************************************
 Enigma Inverse Shifting

    This routine emulates the rottating characteristic of enigma rotors. This
 routine specifically emulate the left to right pre substitution.
 ******************************************************************************/
signed char ishift(signed char a, signed char b, signed char c){
    signed char tmp;

    tmp = a - b;
    if(tmp < 0){
        tmp += MAX_POS;
    }
    tmp = (tmp + c) % MAX_POS;
    return tmp;
}




/*******************************************************************************
 Rotate Forward

    This routine will forward a given rotor position. The forward rotating
 will affect to the substitution process.
 ******************************************************************************/
void rotate_fwd(signed char *rotor_pos, unsigned char mod){
    *rotor_pos = (*rotor_pos + 1) % mod;
}




/*******************************************************************************
 Rotate Backward

    This routine will backward a given rotor position. Thebackward rotating
 will affect to the substitution process.
 ******************************************************************************/
void rotate_bwd(signed char *rotor_pos, unsigned char mod){
    *rotor_pos = (*rotor_pos - 1);
    if(*rotor_pos < 0){
        *rotor_pos += mod;
    }
}




/*******************************************************************************
 Store Configuration

    This routine will store the current applied confiuration of rotors to the
 confiuration buffer located on EEPROM. This action is important to memorize
 the last state of enigma against unexpected electricity cut.
 ******************************************************************************/
void store_config(void){
    backup_rot_l_type = rot_l_type;
    backup_rot_m_type = rot_m_type;
    backup_rot_r_type = rot_r_type;
    backup_rot_l_set  = rot_l_set;
    backup_rot_m_set  = rot_m_set;
    backup_rot_r_set  = rot_r_set;
    backup_rot_x_set  = rot_x_set;
    backup_rot_l_pos  = rot_l_pos;
    backup_rot_m_pos  = rot_m_pos;
    backup_rot_r_pos  = rot_r_pos;
    backup_rot_x_pos  = rot_x_pos;
}




/*******************************************************************************
 Store Configuration

    This routine will restore the stored enigma configuration to its original
 place.
 ******************************************************************************/
void load_config(void){
    rot_l_type = backup_rot_l_type;       /* Put Rotor I on left slot */
    rot_m_type = backup_rot_m_type;       /* Put Rotor I on middle slot */
    rot_r_type = backup_rot_r_type;       /* Put Rotor I on right slot */
    rot_x_set  = backup_rot_x_set;     /* Set Reflector setting to A */
    rot_l_set  = backup_rot_l_set;     /* Set Left Rotor Setting to A */
    rot_m_set  = backup_rot_m_set;     /* Set Middle Rotor Setting to A */
    rot_r_set  = backup_rot_r_set;     /* Set Right Rotor Setting to A */
    rot_x_pos  = backup_rot_x_pos;     /* Set Reflector Position to A */
    rot_l_pos  = backup_rot_l_pos;     /* Set Left Rotor Position to A */
    rot_m_pos  = backup_rot_m_pos;     /* Set Middle Rotor Position to A */
    rot_r_pos  = backup_rot_r_pos;     /* Set Left Rotor Position to A */
}




/*******************************************************************************
 Default Configuration

    This routine is a shortcut of setting enigma configuration to its original
 configuration.
 ******************************************************************************/
void default_config(void){
    rot_l_type = ROTOR_1;       /* Put Rotor I on left slot */
    rot_m_type = ROTOR_2;       /* Put Rotor I on middle slot */
    rot_r_type = ROTOR_3;       /* Put Rotor I on right slot */
    rot_x_set  = 'A' - 'A';     /* Set Reflector setting to A */
    rot_l_set  = 'A' - 'A';     /* Set Left Rotor Setting to A */
    rot_m_set  = 'A' - 'A';     /* Set Middle Rotor Setting to A */
    rot_r_set  = 'A' - 'A';     /* Set Right Rotor Setting to A */
    rot_x_pos  = 'A' - 'A';     /* Set Reflector Position to A */
    rot_l_pos  = 'A' - 'A';     /* Set Left Rotor Position to A */
    rot_m_pos  = 'A' - 'A';     /* Set Middle Rotor Position to A */
    rot_r_pos  = 'A' - 'A';     /* Set Left Rotor Position to A */
}




/*******************************************************************************
 Show Configuration

    This routine will show a formatted current enigma configuration. The given
 configurations are consisted of rotor type placement, rotor setting, and rotor
 position.
 ******************************************************************************/
void show_config(void){
    putchar('\n');
    putsf ("======================");
    putsf (" Enigma Configuration ");
    putsf ("======================");
    printf("Type       %u %u %u\n",                   rot_l_type + 1, rot_m_type + 1, rot_r_type + 1);
    printf("Setting  %c %c %c %c\n", rot_x_set  + 'A', rot_l_set  + 'A', rot_m_set  + 'A', rot_r_set  + 'A');
    printf("Position %c %c %c %c\n", rot_x_pos  + 'A', rot_l_pos  + 'A', rot_m_pos  + 'A', rot_r_pos  + 'A');
    putchar('\n');
}




/*******************************************************************************
 Print Help

    This routine will display a help page prior to the given type of help page
 supplied. The available help pages "about", "license", and "usage".
 ******************************************************************************/
void print_help(unsigned char menu){
    switch(menu){
        case MENU_ABOUT   : putchar('\n');
                            putsf("========================================");
                            putsf("|       The Enigma Railway on AVR      |");
                            putsf("========================================");
                            putsf("Author : Edi Permadi");
                            putsf("Date   : June 13, 2009");
                            putsf("Ver.   : 0.1");
                            putsf("email  : edipermadi@gmail.com");
                            putsf("blog   : http://edipermadi.wordpress.com");
                            putsf("========================================");
                            putchar('\n');
                            getchar();
                            break;
        case MENU_LICENSE : putchar('\n');
                            putsf("========================================");
                            putsf("|              Licensing               |");
                            putsf("========================================");
                            putsf("This Application is is licensed under");
                            putsf("GNU Public License version 3.0, please");
                            putsf("refer to the license for further detail.");
                            putsf("========================================");
                            putchar('\n');
                            getchar();
                            break;
        case MENU_USAGE   : putchar('\n');
                            putsf("========================================");
                            putsf("|   The Enigma Railway on AVR  Usage   |");
                            putsf("========================================");
                            putsf("[?]   Show Help");
                            putsf("[`]   Toggle Auto Show Configuration");
                            putsf("[=]   Load Default Configuration");
                            putsf("[+]   Save Configuration");
                            putsf("[!/1] forward/bacward reflector setting ");
                            putsf("[@/2] forward/bacward left setting ");
                            putsf("[#/3] forward/bacward middle setting ");
                            putsf("[$/4] forward/bacward right setting ");
                            putsf("[%/5] forward/bacward reflector position ");
                            putsf("[^/6] forward/bacward left position ");
                            putsf("[&/7] forward/bacward middle position ");
                            putsf("[*/8] forward/bacward right position ");
                            putsf("[(/9] switch left rotor ");
                            putsf("[)/0] switch middle rotor ");
                            putsf("[_/-] switch right rotor ");
                            putsf("========================================");
                            putchar('\n');
                            break;
    }
}
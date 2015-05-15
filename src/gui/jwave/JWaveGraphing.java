/**
 * JWaveGraphing is distributed under the MIT License (MIT); this file is part of.
 *
 * Copyright (c) 2015 Christian Scheiblich (cscheiblich@gmail.com)
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package gui.jwave;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JWaveGraphing extends JFrame {

  /**
   * @author Christian Scheiblich (cscheiblich@gmail.com)
   * @date 15.05.2015 22:22:10
   */
  private static final long serialVersionUID = 2038965267122479551L;

  /**
   * @author Christian Scheiblich (cscheiblich@gmail.com)
   * @date 15.05.2015 22:22:26
   */
  private JPanel _contentPane;

  /**
   * @author Christian Scheiblich (cscheiblich@gmail.com)
   * @date 15.05.2015 22:47:08
   */
  private JMenuBar _menuBar = new JMenuBar( );

  /**
   * @author Christian Scheiblich (cscheiblich@gmail.com)
   * @date 15.05.2015 22:47:45
   */
  private JMenu _menuMain = new JMenu( "main" );
  private JMenuItem _menuItemExit = new JMenuItem( "exit" );

  /**
   * @author Christian Scheiblich (cscheiblich@gmail.com)
   * @date 15.05.2015 22:49:03
   */
  private JMenu _menuAlgorithm = new JMenu( "algorithm" );
  private JMenuItem _menuItemFWT = new JMenuItem( "Fast Wavelet Transform" );
  private JMenuItem _menuItemWPT = new JMenuItem( "Wavelet Packet Transform" );

  /**
   * @author Christian Scheiblich (cscheiblich@gmail.com)
   * @date 15.05.2015 22:50:55
   */
  private JMenu _menuWavelet = new JMenu( "Wavelet" );
  private JMenuItem _menuItemHaar = new JMenuItem( "Haar" );
  private JMenu _menuDaubechies = new JMenu( "Daubechies" );
  private JMenuItem _menuItemDaub2 = new JMenuItem( "Daub2" );
  private JMenuItem _menuItemDaub3 = new JMenuItem( "Daub3" );
  private JMenuItem _menuItemDaub4 = new JMenuItem( "Daub4" );
  private JMenu _menuCoiflet = new JMenu( "Coiflet" );
  private JMenuItem _menuItemCoif1 = new JMenuItem( "Coif1" );
  private JMenuItem _menuItemCoif2 = new JMenuItem( "Coif2" );
  private JMenuItem _menuItemCoif3 = new JMenuItem( "Coif3" );

  /**
   * @author Christian Scheiblich (cscheiblich@gmail.com)
   * @date 15.05.2015 22:51:44
   */
  private JMenu _menuHelp = new JMenu( "Help" );
  private JMenuItem _menuItemVersion = new JMenuItem( "version" );

  /**
   * Launch the application.
   * 
   * @author Christian Scheiblich (cscheiblich@gmail.com)
   * @date 15.05.2015 22:23:29
   * @param args
   */
  public static void main( String[ ] args ) {

    EventQueue.invokeLater( new Runnable( ) {

      public void run( ) {

        try {
          JWaveGraphing frame = new JWaveGraphing( );
          frame.setVisible( true );
        } catch( Exception e ) {
          e.printStackTrace( );
        } // try

      } // Runnable

    } ); // EventQueue

  } // main

  /**
   * Create the frame.
   */
  public JWaveGraphing( ) {

    setTitle( "JWaveGraphing" );

    setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

    setBounds( 100, 100, 450, 300 );

    setJMenuBar( _menuBar );

    // main
    _menuBar.add( _menuMain );
    _menuItemExit.addActionListener( new ActionListener( ) {
      public void actionPerformed( ActionEvent arg0 ) {

        // raw drop
        System.exit( 0 );

      }
    } );
    _menuMain.add( _menuItemExit );

    // algorithm
    _menuBar.add( _menuAlgorithm );
    _menuAlgorithm.add( _menuItemFWT );
    _menuAlgorithm.add( _menuItemWPT );

    // wavelet
    _menuBar.add( _menuWavelet );
    _menuWavelet.add( _menuItemHaar );
    _menuWavelet.add( _menuDaubechies );
    _menuDaubechies.add( _menuItemDaub2 );
    _menuDaubechies.add( _menuItemDaub3 );
    _menuDaubechies.add( _menuItemDaub4 );
    _menuWavelet.add( _menuCoiflet );
    _menuCoiflet.add( _menuItemCoif1 );
    _menuCoiflet.add( _menuItemCoif2 );
    _menuCoiflet.add( _menuItemCoif3 );

    // help
    _menuBar.add( _menuHelp );
    _menuHelp.add( _menuItemVersion );

    _contentPane = new JPanel( );
    _contentPane.setBorder( new EmptyBorder( 5, 5, 5, 5 ) );
    _contentPane.setLayout( new BorderLayout( 0, 0 ) );
    setContentPane( _contentPane );

  } // JWaveGraphing

} // JWaveGraphing

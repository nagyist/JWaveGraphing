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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import math.jwave.Transform;
import math.jwave.TransformBuilder;

public class JWaveGraphing extends JFrame {

  /**
   * @author Christian Scheiblich (cscheiblich@gmail.com)
   * @date 15.05.2015 22:22:10
   */
  private static final long serialVersionUID = 2038965267122479551L;

  /**
   * JWave objects - Transform, BasicTransform, and Wavelet.
   * 
   * @author Christian Scheiblich (cscheiblich@gmail.com)
   * @date 15.05.2015 23:00:37
   */
  protected String _selectedTransform = "Fast Wavelet Transform";
  protected String _selectedWavelet = "Haar";

  /**
   * @author Christian Scheiblich (cscheiblich@gmail.com)
   * @date 15.05.2015 22:22:26
   */
  private JPanel _contentPanel;
  private JScrollPane _scrollPane = new JScrollPane( );

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
  private JMenuItem _menuItemLoad = new JMenuItem( "load" );
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
  private JMenuItem _menuItemDaub2 = new JMenuItem( "Daubechies 2" );
  private JMenuItem _menuItemDaub3 = new JMenuItem( "Daubechies 3" );
  private JMenuItem _menuItemDaub4 = new JMenuItem( "Daubechies 4" );
  private JMenu _menuCoiflet = new JMenu( "Coiflet" );
  private JMenuItem _menuItemCoif1 = new JMenuItem( "Coiflet 1" );
  private JMenuItem _menuItemCoif2 = new JMenuItem( "Coiflet 2" );
  private JMenuItem _menuItemCoif3 = new JMenuItem( "Coiflet 3" );

  /**
   * @author Christian Scheiblich (cscheiblich@gmail.com)
   * @date 15.05.2015 22:51:44
   */
  private JMenu _menuHelp = new JMenu( "Help" );
  private JMenuItem _menuItemVersion = new JMenuItem( "version" );

  /**
   * @author Christian Scheiblich (cscheiblich@gmail.com)
   * @date 15.05.2015 23:38:06
   */
  private final JButton _buttonForward = new JButton( "forward" );
  private final JButton _buttonReverse = new JButton( "reverse" );

  /**
   * @author Christian Scheiblich (cscheiblich@gmail.com)
   * @date 15.05.2015 23:49:19
   */
  protected BufferedImage _bufferedImage = null;

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
        System.exit( 0 ); // raw drop
      }
    } );
    _menuItemLoad.addActionListener( new ActionListener( ) {
      public void actionPerformed( ActionEvent e ) {

        JFileChooser jFileChooser = new JFileChooser( );
        int returnVal = jFileChooser.showOpenDialog( _contentPanel );
        if( returnVal == JFileChooser.APPROVE_OPTION ) {

          File file = jFileChooser.getSelectedFile( );

          _bufferedImage = loadBufferedImage( file );

          _contentPanel.removeAll( );
          paintBufferedImage( _contentPanel, _bufferedImage );
          _contentPanel.updateUI( );

        } // if
      }
    } );

    _menuMain.add( _menuItemLoad );
    _menuMain.add( _menuItemExit );

    // algorithm
    _menuBar.add( _menuAlgorithm );
    _menuItemFWT.addActionListener( new ActionListener( ) {
      public void actionPerformed( ActionEvent e ) {
        _selectedTransform = "Fast Wavelet Transform";
      }
    } );
    _menuAlgorithm.add( _menuItemFWT );
    _menuItemWPT.addActionListener( new ActionListener( ) {
      public void actionPerformed( ActionEvent e ) {
        _selectedTransform = "Wavelet Packet Transform";
      }
    } );
    _menuAlgorithm.add( _menuItemWPT );

    // wavelet
    _menuBar.add( _menuWavelet );
    _menuItemHaar.addActionListener( new ActionListener( ) {
      public void actionPerformed( ActionEvent e ) {
        _selectedWavelet = "Haar";
      }
    } );
    _menuWavelet.add( _menuItemHaar );
    _menuWavelet.add( _menuDaubechies );
    _menuItemDaub2.addActionListener( new ActionListener( ) {
      public void actionPerformed( ActionEvent e ) {
        _selectedWavelet = "Daubechies 2";
      }
    } );
    _menuDaubechies.add( _menuItemDaub2 );
    _menuItemDaub3.addActionListener( new ActionListener( ) {
      public void actionPerformed( ActionEvent e ) {
        _selectedWavelet = "Daubechies 3";
      }
    } );
    _menuDaubechies.add( _menuItemDaub3 );
    _menuItemDaub4.addActionListener( new ActionListener( ) {
      public void actionPerformed( ActionEvent e ) {
        _selectedWavelet = "Daubechies 4";
      }
    } );
    _menuDaubechies.add( _menuItemDaub4 );
    _menuWavelet.add( _menuCoiflet );
    _menuItemCoif1.addActionListener( new ActionListener( ) {
      public void actionPerformed( ActionEvent e ) {
        _selectedWavelet = "Coiflet 1";
      }
    } );
    _menuCoiflet.add( _menuItemCoif1 );
    _menuItemCoif2.addActionListener( new ActionListener( ) {
      public void actionPerformed( ActionEvent e ) {
        _selectedWavelet = "Coiflet 2";
      }
    } );
    _menuCoiflet.add( _menuItemCoif2 );
    _menuItemCoif3.addActionListener( new ActionListener( ) {
      public void actionPerformed( ActionEvent e ) {
        _selectedWavelet = "Coiflet 3";
      }
    } );
    _menuCoiflet.add( _menuItemCoif3 );

    // help
    _menuBar.add( _menuHelp );
    _menuItemVersion.addActionListener( new ActionListener( ) {
      public void actionPerformed( ActionEvent e ) {

        // pop up a window with version

      }
    } );
    _menuHelp.add( _menuItemVersion );

    _buttonForward.addActionListener( new ActionListener( ) {
      public void actionPerformed( ActionEvent e ) {

        // quick an dirty
        Transform t =
            TransformBuilder.create( _selectedTransform, _selectedWavelet );
        //        BasicTransform basicTransform = t.getBasicTransform( );
        //        t = new Transform( new AncientEgyptianDecomposition( basicTransform ) );

        int width = _bufferedImage.getWidth( );
        int height = _bufferedImage.getHeight( );

//        int[ ] arrDeCompWidth = null;
//        int[ ] arrDeCompHeight = null;
//
//        try {
//          arrDeCompWidth = MathToolKit.decompose( width );
//          arrDeCompHeight = MathToolKit.decompose( height );
//        } catch( JWaveException e1 ) {
//          e1.printStackTrace( );
//        }

        int[ ][ ] matImageRGBintger =
            convertBufferdImage2matixIntegerCodedRGB( _bufferedImage );

        double[ ][ ] matImagRGBdouble =
            convertIntMat2DblMat( matImageRGBintger );

        double[ ][ ] matImageRGBdoubleForward = t.forward( matImagRGBdouble );

        int[ ][ ] matImageRGBintegerForward =
            convertDblMat2IntMat( matImageRGBdoubleForward );

        // TODO some bug due to getRGB of BufferedImage ..
        BufferedImage bufferedImage = new BufferedImage( width, height, BufferedImage.TYPE_INT_ARGB);
        for( int i = 0; i < matImageRGBintegerForward.length; i++ ) {
          for( int j = 0; j < matImageRGBintegerForward[ 0 ].length; j++ ) {
            int pixel = matImageRGBintegerForward[ j ][ i ];
            bufferedImage.setRGB( i, j, pixel );
          }
        }
        
        _contentPanel.removeAll( );
        paintBufferedImage( _contentPanel, bufferedImage );
        _contentPanel.updateUI( );

      }
    } );

    _menuBar.add( _buttonForward );
    _buttonReverse.addActionListener( new ActionListener( ) {
      public void actionPerformed( ActionEvent e ) {

//        Transform t =
//            TransformBuilder.create( _selectedTransform, _selectedWavelet );

        // TODO reverse transform of data - not given yet

      }
    } );

    _menuBar.add( _buttonReverse );

    _contentPanel = new JPanel( );
    _contentPanel.setBorder( new EmptyBorder( 5, 5, 5, 5 ) );
    setContentPane( _contentPanel );
    _contentPanel.setLayout( new BorderLayout( 0, 0 ) );
    _contentPanel.add( _scrollPane, BorderLayout.CENTER );

  } // JWaveGraphing

  /**
   * loading from File to a buffered image
   * 
   * @author Christian Scheiblich (cscheiblich@gmail.com)
   * @date 15.05.2015 23:47:37
   * @param file
   * @return
   */
  protected BufferedImage loadBufferedImage( File file ) {

    BufferedImage bufferedImage = null;
    try {
      bufferedImage = ImageIO.read( file );
    } catch( IOException exception ) {
      exception.printStackTrace( );
    } // try

    return bufferedImage;

  } // loadBufferedImage

  protected void paintBufferedImage( JPanel contentPanel,
      BufferedImage bufferedImage ) {

    contentPanel.add( new JLabel( new ImageIcon( bufferedImage ) ) );

  } // paintBufferedImage

  /**
   * convert image to rgb
   * 
   * @author Christian Scheiblich (cscheiblich@gmail.com)
   * @date 16.05.2015 00:23:09
   * @param image
   * @return
   */
  private int[ ][ ] convertBufferdImage2matixIntegerCodedRGB(
      BufferedImage image ) {

    int width = image.getWidth( );
    int height = image.getHeight( );

    // Color color = new Color( image.getRGB(  x, y ) );
    // Color[][] colors = new Color[ width ][ height ];

    int[ ][ ] matIntCodedRGB = new int[ width ][ height ];

    //    22222222 111111
    //    bitpos    32109876 54321098 76543210
    //    ------------------------------------
    //    bits      RRRRRRRR GGGGGGGG BBBBBBBB

    // Components will be in the range of 0..255:
    //    int blue = color & 0xff;
    //    int green = ( color & 0xff00 ) >> 8;
    //    int red = ( color & 0xff0000 ) >> 16;

    //    Your color is color = -16755216 which has:
    //
    //      blue : 240         // Strong blue
    //      green:  85         // A little green mixed in
    //      red  :   0         // No red component at all
    //      alpha: 255         // Completely opaque

    for( int row = 0; row < height; row++ ) {
      for( int col = 0; col < width; col++ ) {
        matIntCodedRGB[ col ][ row ] = image.getRGB( col, row );
      }
    }

    return matIntCodedRGB;
  }

  /**
   * convert integer matrix to double matrix
   * 
   * @author Christian Scheiblich (cscheiblich@gmail.com)
   * @date 16.05.2015 00:24:24
   * @param mat
   * @return
   */
  private double[ ][ ] convertIntMat2DblMat( int[ ][ ] mat ) {

    double[ ][ ] dblMat = new double[ mat.length ][ mat[ 0 ].length ];

    for( int i = 0; i < mat.length; i++ )
      for( int j = 0; j < mat[ 0 ].length; j++ )
        dblMat[ i ][ j ] = (double)mat[ i ][ j ];

    return dblMat;

  }

  /**
   * convert double matrix to integer matrix
   * 
   * @author Christian Scheiblich (cscheiblich@gmail.com)
   * @date 16.05.2015 00:24:40
   * @param mat
   * @return
   */
  private int[ ][ ] convertDblMat2IntMat( double[ ][ ] mat ) {

    int[ ][ ] intMat = new int[ mat.length ][ mat[ 0 ].length ];

    for( int i = 0; i < mat.length; i++ )
      for( int j = 0; j < mat[ 0 ].length; j++ )
        intMat[ i ][ j ] = (int)mat[ i ][ j ];

    return intMat;

  }

} // JWaveGraphing

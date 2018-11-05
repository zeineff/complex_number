package maths;

public class Complex{
    /** The real part of {@code this}.*/
    double r;
    /** The imaginary part of {@code this}.*/
    double i;
    /** The magnitude of {@code this}.*/
    double m;
    /** The angle of {@code this}.*/
    double a;
    
    public Complex(double r, double i){
        this(r, i, true);
    }
    
    public Complex(double x, double y, boolean cartesian){
        if (cartesian){
            r = x;
            i = y;
            
            update_polar();
        }else{
            m = x;
            a = y;
            
            update_cartesian();
        }
    }
    
    private void update_cartesian(){
        r = Math.cos(a) * m;
        i = Math.sin(a) * m;
        
        if (-Math.PI >= a || a > Math.PI)
            a = Math.atan2(i, r);
    }
    
    private void update_polar(){
        a = Math.atan2(i, r);
        m = Math.sqrt(r*r + i*i);
    }
    
    /**Adds a complex number expressed using Cartesian coordinates to
     * {@code this}.
     *
     * @param r     The real part of the complex number to add
     * @param i     The imaginary part of the complex number to add
     */
    public void add(double r, double i){
        this.r += r;
        this.i += i;
        
        update_polar();
    }
    
    /**Adds a real number to {@code this}.
     *
     * @param r     The real number to add
     */
    public void add(double r){
        add(r, 0);
    }
    
    /**Adds a complex number to {@code this}.
     *
     * @param z     The complex number to add
     */
    public void add(Complex z){
        add(z.r, z.i);
    }
    
    /**Adds a complex number expressed using polar coordinates to {@code this}.
     *
     * @param m     The magnitude of the complex number to add
     * @param a     The angle of the complex number to add
     */
    public void add_polar(double m, double a){
        r = Math.cos(this.a) * this.m + Math.cos(a) * m;
        i = Math.sin(this.a) * this.m + Math.sin(a) * m;
        
        update_polar();
    }
    
    
    
    /**Subtract a complex number expressed using Cartesian coordinates from
     * {@code this}.
     *
     * @param r     The real part of the complex number to subtract
     * @param i     The imaginary part of the complex number to subtract
     */
    public void sub(double r, double i){
        add(-r, -i);
    }
    
    /**Subtract a real number from {@code this}.
     *
     * @param r     The real number to subtract
     */
    public void sub(double r){
        add(-r, 0);
    }
    
    /**Subtracts a complex number from {@code this}.
     *
     * @param z     The complex number to subtract
     */
    public void sub(Complex z){
        add(-z.r, -z.i);
    }
    
    /**Subtracts a complex number expressed using polar coordinates from
     * {@code this}.
     *
     * @param m     The magnitude of the complex number to subtract
     * @param a     The angle of the complex number to subtract
     */
    public void sub_polar(double m, double a){
        add_polar(-m, a);
    }
    
    
    
    /**Multiplies {@code this} by a complex number expressed using Cartesian
     * coordinates.
     *
     * @param r     The real part of the complex number to multiply by
     * @param i     The imaginary part of the complex number to multiply by
     */
    public void mul(double r, double i){
        double real = this.r * r - this.i * i;
        
        this.i = this.r * i + this.i * r;
        this.r = real;
        
        update_polar();
    }
    
    /**Multiplies {@code this} by a real number.
     *
     * @param r     The real number to multiply by
     */
    public void mul(double r){
        this.r *= r;
        this.i *= r;
        
        update_polar();
    }
    
    /**Multiplies {@code this} by a complex number.
     *
     * @param z     The complex number to multiply by
     */
    public void mul(Complex z){
        m *= z.m;
        a += z.a;
        
        update_cartesian();
    }
    
    /**Multiplies {@code this} by a complex number expressed using polar
     * coordinates.
     *
     * @param m     The magnitude of the complex number to multiply by
     * @param a     The angle of the complex number to multiply by
     */
    public void mul_polar(double m, double a){
        this.m *= m;
        this.a += a;
        
        update_cartesian();
    }
    
    
    
    /**Divides {@code this} by a complex number expressed using Cartesian
     * coordinates.
     *
     * @param r     The real part of the complex number to divide by
     * @param i     The imaginary part of the complex number to divide by
     */
    public void div(double r, double i){
        double divide = 1 / (r*r + i*i);
        
        double real = (this.r * r + this.i * i) * divide;
        
        this.i = (this.i * r - this.r * i) * divide;
        this.r = real;
        
        update_polar();
    }
    
    /**Divides {@code this} by a real number.
     *
     * @param r     The real number to divide by
     */
    public void div(double r){
        mul(1 / r);
    }
    
    /**Divides {@code this} by a complex number.
     *
     * @param z     The complex number to divide by
     */
    public void div(Complex z){
        mul_polar(1 / z.m, -z.a);
    }
    
    /**Divides {@code this} by a complex number expressed using polar
     * coordinates.
     *
     * @param m     The magnitude of the complex number to divide by
     * @param a     The angle of the complex number to divide by
     */
    public void div_polar(double m, double a){
        mul(1 / m, -a);
    }
    
    
    
    /**Raises {@code this} to the power of a complex number expressed using
     * Cartesian coordinates.
     *
     * @param r     The real part of the complex exponent
     * @param i     The imaginary part of the complex exponent
     */
    public void pow(double r, double i){
        double ln = Math.log(m);
        
        m = Math.exp(ln * r - a * i);
        a = ln * i + a * r;
        
        update_cartesian();
    }
    
    /**Raises {@code this} to the power of a real number.
     *
     * @param r     The real number exponent
     */
    public void pow(double r){
        pow(r, 0);
    }
    
    /**Raises {@code this} to the power of a complex number.
     *
     * @param z     The complex exponent
     */
    public void pow(Complex z){
        pow(z.m, z.a);
    }
    
    /**Raises {@code this} to the power of a complex number expressed using
     * polar form.
     *
     * @param m     The magnitude of the complex exponent
     * @param a     The angle of the complex exponent
     */
    public void pow_polar(double m, double a){
        double ln = Math.log(this.m);
        double cos = Math.cos(a);
        double sin = Math.sin(a);
        
        this.m = Math.exp(m * (ln * cos - this.a * sin));
        this.a = m * (ln * sin + this.a * cos);
        
        update_cartesian();
    }
    
    
    
    /**Returns a new complex number equal to the natural log of a complex
     * number expressed using Cartesian coordinates.
     *
     * @param a     The real part of the complex number to take the natural log
     *              of
     * @param b     The imaginary part of the complex number to take the natural
     *              log of
     * @return      The natural log of {@code a} + {@code b}i
     */
    public static Complex ln(double a, double b){
        return ln_polar(Math.sqrt(a*a + b*b), Math.atan2(b, a));
    }
    
    /**Returns a new complex number equal to the natural log of a real number.
     *
     * @param a     The real number to take the natural log of
     * @return      The natural log of {@code a} + 0i
     */
    public static Complex ln(double a){
        return ln_polar(a, (a >= 0 ? 0 : Math.PI));
    }
    
    /**Returns a new complex number equal to the natural log of a complex
     * number.
     *
     * @param z     The complex number to take the natural log of
     * @return      The natural log of {@code z}
     */
    public static Complex ln(Complex z){
        return ln_polar(z.m, z.m);
    }
    
    /**Returns a new complex number equal to the natural log of a complex
     * number expressed using polar coordinates.
     *
     * @param a     The magnitude of the complex number to take the natural log
     *              of
     * @param b     The angle of the complex number to take the natural log of
     * @return      The natural base of {@code a} ∠ {@code b}
     */
    public static Complex ln_polar(double a, double b){
        return new Complex(Math.log(Math.abs(a)), b);
    }
    
    
    
    /**Returns a new complex number equal to the log in some complex base of a
     * complex number. Both the base and complex number to take the log of are
     * expressed using their Cartesian coordinates.
     *
     * @param a    The real part of the base
     * @param b    The imaginary part of the base
     * @param c    The real part of the number to take the log of
     * @param d    The imaginary part of the number to take the log of
     * @return      The log base ({@code a} + {@code b}i) of ({@code c} + 
     *              {@code d}i)
     */
    public static Complex log(double a, double b, double c, double d){
        return log_polar(Math.sqrt(a*a + b*b), Math.atan2(b, a),
                         Math.sqrt(c*c + d*d), Math.atan2(d, c));
    }
    
    /**Returns a new complex number equal to the log in some complex base of a
     * complex number.
     *
     * @param z1    The complex base
     * @param z2    The complex number to take the log of
     * @return      The log base {@code z1} of {@code z2}
     */
    public static Complex log(Complex z1, Complex z2){
        return log_polar(z1.m, z1.a, z2.m, z2.a);
    }
    
    /**Returns a new complex number equal to the log in some complex base of a
     * complex number. Both the base and complex number to take the log of are
     * expressed using their polar coordinates.
     *
     * @param m1    The magnitude of the base
     * @param a1    The angle of the base in radians
     * @param m2    The magnitude of the number to take the log of
     * @param a2    The angle of the number to take the log of in radians
     * @return      The log base ({@code m1} ∠ {@code a1}) of ({@code m2} ∠ 
     *              {@code a2})
     */
    public static Complex log_polar(double m1, double a1, double m2, double a2){
        Complex z = ln_polar(m2, a2);
        z.div(ln_polar(m1, a1));
        return z;
    }
    
    
    /**Creates a textual representation of this complex number.
     * 
     * @return      A string representation of {@code this} in the form a + bi
     *              where i is the imaginary constant
     */
    @Override
    public String toString(){
        return String.format("%s%.5f %c %.5f i", (r >= 0 ? " " : ""), r, (i < 0 ? '-' : '+'), Math.abs(i));
    }
}

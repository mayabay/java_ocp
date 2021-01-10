/**
 * Virtual Method Invocation
 */
package pkg_1;

/**
 *
 */
public class VirtualMethodsTest1 {

	interface VMInter1 {
		default String test1() { return "VMInter1 test1"; }
		default String inter1() { return "VMInter1 inter1"; }
		static String interStatic1() { return "VMInter1 interStatic1"; }
	}
	
	interface VMInter2 {
		default String test1() { return "VMInter1 test1"; }
	}
	
	class VM_A implements VMInter1, VMInter2 {
		//String test1() { return "VM_A"; }
		public String test1() { 
			return VMInter1.super.test1();
			//return "VM_A"; 
		}
	}
	
	class VM_B extends VM_A {
		public String test1() { 
			//return VMInter1.super.test1();	
				// No enclosing instance of the type VirtualMethodsTest1.VMInter1 is accessible in scope
			return super.test1();		// call overwritten parents version
			///return "VM_B"; 			// my version
			}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		VirtualMethodsTest1 vmt1 = new VirtualMethodsTest1();
		
		VM_B vm_b =  vmt1.new VM_B();
		vmt1.test1(vm_b);

	}

	private void test1( VirtualMethodsTest1.VM_B vm_b )  {
		
		System.out.println( vm_b.test1() );
		
		System.out.println( ((VM_A)vm_b) .test1() );	// 
		//System.out.println( vm_b.super.test1(); );
		
		System.out.println( ((VMInter1)vm_b) .test1() );
		
		System.out.println( ((VMInter1)vm_b) .inter1() );
		
		System.out.println( VMInter1.interStatic1() );
		
	}
	
}
